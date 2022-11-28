package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import java.util.Arrays;

import static java.lang.System.console;
import static java.lang.System.out;

//./gradlew.bat run --args="f f f f f f r r f f f f f f f f f r f f f f f f f r f l f f f f f f f b f f f f f f f f"

public class App extends Application implements IPositionChangeObserver{
    private final OptionsParser commandParser = new OptionsParser();
    private final MapBoundary boundary = new MapBoundary();
    private final IWorldMap map = new GrassField(10, boundary);
    private SimulationEngine engine;

    GridPane grid = new GridPane();

    private final int gridWidth = 1000;
    private final int gridHeight = 800;
    private final int sceneWidth = gridWidth;
    private final int sceneHeight = gridHeight + 150;
    private final double borderWidth = 0.5;
    private final int moveDelay = 250;

    @Override
    public void init(){
        try {
            MoveDirection[] directions = commandParser.parse(getParameters().getRaw());
            Vector2d[] positions = { new Vector2d(0, 0), new Vector2d(2, 6), new Vector2d(8, 3)};
            this.engine = new SimulationEngine(directions, map, positions, boundary, moveDelay);
            for (Animal animal : this.engine.getAnimalsList()){
                animal.addObserver(this);
            }

        } catch (IllegalArgumentException ex) {
            out.println(ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage){
        TextField movesInput = new TextField();
        movesInput.setPrefWidth(Math.floor(this.sceneWidth / 2));
        movesInput.setPromptText("");


        Button startButton = new Button("START");
        startButton.setOnAction(action -> {
            String[] args = movesInput.getText().split(" ");
            MoveDirection[] directions = commandParser.parse(Arrays.stream(args).toList());
            this.engine.setMoves(directions);
            Thread engineThread = new Thread(engine);
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

        Scene scene = new Scene(vBox, sceneWidth, sceneHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World");
        primaryStage.show();


    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Platform.runLater(() -> {

            while(grid.getRowConstraints().size() > 0){
                grid.getRowConstraints().remove(0);
            }

            while(grid.getColumnConstraints().size() > 0){
                grid.getColumnConstraints().remove(0);
            }

            Node node = grid.getChildren().get(0);
            grid.getChildren().clear();
            grid.getChildren().add(0,node);

            renderMap();
        });
    }

    private void renderMap(){
        int rows;
        int cols;
        int cellSize;

        Vector2d leftBottomCorner = boundary.getLowerLeftCorner();
        Vector2d rightUpperCorner = boundary.getUpperRightCorner();
        rows = (rightUpperCorner.subtract(leftBottomCorner)).y + 2;
        cols = (rightUpperCorner.subtract(leftBottomCorner)).x + 2;
        cellSize = (int) Math.floor(Math.min(calculateCellSize(gridWidth, cols), calculateCellSize(gridHeight, rows)));
        grid.setGridLinesVisible(true);

        for (int i = 0; i < cols; i++) grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (int i = 0; i < rows; i++) grid.getRowConstraints().add(new RowConstraints(cellSize));

        addGridReference(rows, leftBottomCorner.y, cols, leftBottomCorner.x, cellSize);
        addMapObjects(rows, leftBottomCorner.y, cols, leftBottomCorner.x, cellSize);
    }

    private double calculateCellSize(int availableLength, int numberOfCells){
        return (availableLength - numberOfCells * borderWidth) / numberOfCells;
    }

    private void addGridReference(int rows, int rowsStart, int cols, int colsStart, float cellSize){
        Label xyLabel = new Label("y\\x");
        xyLabel.setFont(new Font(cellSize / 2));
        xyLabel.setTextFill(Color.web("white"));
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        grid.add(xyLabel, 0, 0, 1, 1);

        Label lbl;
        for (int i = 0; i < cols - 1; i++){
            lbl = new Label(String.valueOf(colsStart + i));
            lbl.setFont(new Font(cellSize / 2));
            lbl.setTextFill(Color.web("white"));
            GridPane.setHalignment(lbl, HPos.CENTER);
            grid.add(lbl, i + 1, 0, 1, 1);
        }

        for (int i = 0; i < rows - 1; i++){
            lbl = new Label(String.valueOf(rows + rowsStart - 2 - i));
            lbl.setFont(new Font(cellSize / 2));
            lbl.setTextFill(Color.web("white"));
            GridPane.setHalignment(lbl, HPos.CENTER);
            grid.add(lbl, 0, i + 1, 1, 1);

        }
    }

    private void addMapObjects(int rows, int rowsStart, int cols, int colsStart, float cellSize){

        VBox box;

        for (int i = 0; i < rows - 1; i++){
            for (int j = 0; j < cols - 1; j++){
                box = new GuiElementBox((int) ((int) cellSize - Math.round(borderWidth * 2)),
                        wrapUrl(map.objectAt(new Vector2d(colsStart + j,
                        rows + rowsStart - 2 - i)))).getBox();
                GridPane.setHalignment(box, HPos.CENTER);
                grid.add(box, j + 1, i + 1, 1, 1);
            }
        }
    }

    private String wrapUrl(Object MapElement){
        if (MapElement == null){
            return "src/main/resources/dirt.png";
        } else {
            return ((IMapElement) MapElement).getImageUrl();
        }
    }
}