package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import static java.lang.System.out;

//./gradlew.bat run --args="f f f f f f r r f f f f f f f f f r f f f f f f f r f l f f f f f f f b f f f f f f f f"

public class App extends Application {
    private final OptionsParser commandParser = new OptionsParser();
    private final MapBoundary boundary = new MapBoundary();
    private final IWorldMap map = new GrassField(10, boundary);
    private IEngine engine;
    private int cellSize = 50;
    private static final double borderWidth = 0.5;

    @Override
    public void init(){
        try {
            MoveDirection[] directions = commandParser.parse(getParameters().getRaw());
            Vector2d[] positions = { new Vector2d(0, 0), new Vector2d(2, 6), new Vector2d(8, 3)};
            this.engine = new SimulationEngine(directions, map, positions, boundary);
            engine.run();

        } catch (IllegalArgumentException ex) {
            out.println(ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage){
        int rows;
        int cols;

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: gray");

        Vector2d leftBottomCorner = boundary.getLowerLeftCorner();
        Vector2d rightUpperCorner = boundary.getUpperRightCorner();
        rows = (rightUpperCorner.subtract(leftBottomCorner)).y + 2;
        cols = (rightUpperCorner.subtract(leftBottomCorner)).x + 2;
        grid.setGridLinesVisible(true);

        for (int i = 0; i < cols; i++) grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (int i = 0; i < rows; i++) grid.getRowConstraints().add(new RowConstraints(cellSize));

        addGridReference(grid, rows, leftBottomCorner.y, cols, leftBottomCorner.x, cellSize);
        addMapObjects(grid, rows, leftBottomCorner.y, cols, leftBottomCorner.x, cellSize);

        Scene scene = new Scene(grid, cols * (cellSize + borderWidth), rows * (cellSize + borderWidth));
        primaryStage.setScene(scene);
        primaryStage.setTitle("World");
        primaryStage.show();
    }

    private void addGridReference(GridPane grid, int rows, int rowsStart, int cols, int colsStart, float cellSize){
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

    private void addMapObjects(GridPane grid, int rows, int rowsStart, int cols, int colsStart, float cellSize){
        Label lbl;

        for (int i = 0; i < rows - 1; i++){
            for (int j = 0; j < cols - 1; j++){
                lbl = new Label(stringifyMapElement(map.objectAt(new Vector2d(colsStart + j,
                        rows + rowsStart - 2 - i))));
                lbl.setFont(new Font(cellSize / 2));
                lbl.setTextFill(Color.web("white"));
                GridPane.setHalignment(lbl, HPos.CENTER);
                grid.add(lbl, j + 1, i + 1, 1, 1);
            }
        }
    }

    private String stringifyMapElement(Object MapElement){
        if (MapElement == null){
            return "";
        } else {
            return MapElement.toString();
        }
    }
}