package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import java.util.Arrays;
import static java.lang.System.out;

//./gradlew.bat run --args="f f f f f f r r f f f f f f f f f r f f f f f f f r f l f f f f f f f b f f f f f f f f"

public class App extends Application implements IPositionChangeObserver{
    private final OptionsParser commandParser = new OptionsParser();
    private final MapBoundary boundary = new MapBoundary();
    private final IWorldMap map = new GrassField(10, boundary);
    private SimulationEngine engine;

    GridPane grid = new GridPane();

    private final int gridWidth = 800;
    private final int gridHeight = 600;
    private final int sceneWidth = this.gridWidth;
    private final int sceneHeight = this.gridHeight + 150;
    private final double borderWidth = 0.5;
    private final int moveDelay = 250;

    @Override
    public void init(){
        try {
            MoveDirection[] directions = this.commandParser.parse(getParameters().getRaw());
            Vector2d[] positions = { new Vector2d(0, 0), new Vector2d(2, 6), new Vector2d(8, 3)};
            this.engine = new SimulationEngine(directions, this.map, positions, this.boundary, this.moveDelay);
            for (Animal animal : this.engine.getAnimalsList()){
                animal.addObserver(this);
            }

        } catch (IllegalArgumentException ex) {
            out.println(ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage){
        grid.setGridLinesVisible(true);
        BackgroundImage image = new BackgroundImage(new Image("dirt.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        grid.setMaxHeight(0); grid.setMaxWidth(0);
        this.grid.setBackground(new Background(image));


        TextField movesInput = new TextField();
        movesInput.setPrefWidth(Math.floor(this.sceneWidth / 2));
        movesInput.setPromptText("");

        Button startButton = new Button("START");
        startButton.setOnAction(action -> {
            String[] args = movesInput.getText().split(" ");
            MoveDirection[] directions = this.commandParser.parse(Arrays.stream(args).toList());
            this.engine.setMoves(directions);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(movesInput, startButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-font-size: 20px");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(this.grid, hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #56565e;");
        VBox.setMargin(hBox, new Insets(50, 0, 50, 0));

        renderMap();

        Scene scene = new Scene(vBox, this.sceneWidth, this.sceneHeight);

        primaryStage.setScene(scene);
        primaryStage.setTitle("World");
        primaryStage.show();


    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Platform.runLater(() -> {

            while(this.grid.getRowConstraints().size() > 0){
                this.grid.getRowConstraints().remove(0);
            }

            while(this.grid.getColumnConstraints().size() > 0){
                this.grid.getColumnConstraints().remove(0);
            }

            Node node = this.grid.getChildren().get(0);
            this.grid.getChildren().clear();
            this.grid.getChildren().add(0,node);

            renderMap();
        });
    }

    private void renderMap(){
        int rows;
        int cols;
        int cellSize;

        Vector2d leftBottomCorner = this.boundary.getLowerLeftCorner();
        Vector2d rightUpperCorner = this.boundary.getUpperRightCorner();
        rows = (rightUpperCorner.subtract(leftBottomCorner)).y + 2;
        cols = (rightUpperCorner.subtract(leftBottomCorner)).x + 2;
        cellSize = (int) Math.floor(Math.min(calculateCellSize(this.gridWidth, cols),
                calculateCellSize(this.gridHeight, rows)));


        for (int i = 0; i < cols; i++) this.grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (int i = 0; i < rows; i++) this.grid.getRowConstraints().add(new RowConstraints(cellSize));

        addGridReference(rows, leftBottomCorner.y, cols, leftBottomCorner.x, cellSize);
        addMapObjects(rows, leftBottomCorner.y, cols, leftBottomCorner.x, cellSize);
    }

    private double calculateCellSize(int availableLength, int numberOfCells){
        return (availableLength - numberOfCells * this.borderWidth) / numberOfCells;
    }

    private void addGridReference(int rows, int rowsStart, int cols, int colsStart, float cellSize){
        Label xyLabel = new Label("y\\x");
        xyLabel.setFont(new Font(cellSize / 2));
        xyLabel.setTextFill(Color.web("white"));
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        this.grid.add(xyLabel, 0, 0, 1, 1);

        Label lbl;
        for (int i = 0; i < cols - 1; i++){
            lbl = new Label(String.valueOf(colsStart + i));
            lbl.setFont(new Font(cellSize / 2));
            lbl.setTextFill(Color.web("white"));
            GridPane.setHalignment(lbl, HPos.CENTER);
            this.grid.add(lbl, i + 1, 0, 1, 1);
        }

        for (int i = 0; i < rows - 1; i++){
            lbl = new Label(String.valueOf(rows + rowsStart - 2 - i));
            lbl.setFont(new Font(cellSize / 2));
            lbl.setTextFill(Color.web("white"));
            GridPane.setHalignment(lbl, HPos.CENTER);
            this.grid.add(lbl, 0, i + 1, 1, 1);

        }
    }

    private void addMapObjects(int rows, int rowsStart, int cols, int colsStart, float cellSize){
        VBox box;

        for (int i = 0; i < rows - 1; i++){
            for (int j = 0; j < cols - 1; j++){
                Object element = this.map.objectAt(new Vector2d(colsStart + j, rows + rowsStart - 2 - i));
                if (element != null){
                    box = new GuiElementBox((int) ((int) cellSize - Math.round(this.borderWidth * 2)),
                            (IMapElement) element).getBox();
                    GridPane.setHalignment(box, HPos.CENTER);
                    this.grid.add(box, j + 1, i + 1, 1, 1);
                }
            }
        }
    }
}