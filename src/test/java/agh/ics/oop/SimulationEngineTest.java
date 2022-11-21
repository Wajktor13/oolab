package agh.ics.oop;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SimulationEngineTest {
    private MapBoundary boundary;

    @BeforeEach
    void setup(){
        this.boundary = new MapBoundary();
    }

    @Test
    void rectangularMapCreatingAndPlacingAnimals(){
        MoveDirection[] moves = {MoveDirection.RIGHT, MoveDirection.BACKWARD};
        Vector2d[] initialPositions = {new Vector2d(1, 2), new Vector2d(2, 2), new Vector2d(2, 0)};
        RectangularMap map = new RectangularMap(3, 3, boundary);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions, boundary);

        assertTrue(map.isOccupied(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(0, 1)));
        assertFalse(map.isOccupied(new Vector2d(0, 2)));
        assertFalse(map.isOccupied(new Vector2d(1, 0)));
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
        assertFalse(map.isOccupied(new Vector2d(2, 1)));
    }

    @Test
    void rectangularMapMovingAnimals1(){
        MoveDirection[] moves = {MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD};
        Vector2d[] initialPositions = {new Vector2d(1, 2), new Vector2d(2, 1), new Vector2d(1, 0)};
        RectangularMap map = new RectangularMap(3, 3, boundary);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions, boundary);

        engine.run();

        assertTrue(map.isOccupied(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(1, 1)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(0, 1)));
        assertFalse(map.isOccupied(new Vector2d(0, 2)));
        assertFalse(map.isOccupied(new Vector2d(1, 0)));
        assertFalse(map.isOccupied(new Vector2d(2, 1)));
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void rectangularMapMovingAnimals2(){
        OptionsParser op = new OptionsParser();
        MoveDirection[] moves = op.parse(List.of("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f",
                "f", "f", "f", "f", "f"));
        Vector2d[] initialPositions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        RectangularMap map = new RectangularMap(10, 5, boundary);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions, boundary);

        engine.run();

        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
        assertEquals(((Animal)map.objectAt(new Vector2d(2, 0))).getDirection(), MapDirection.SOUTH);
        assertEquals(((Animal)map.objectAt(new Vector2d(3, 4))).getDirection(), MapDirection.NORTH);

        for (int x = 0; x < 10; x++){
            for (int y = 0; y < 5; y++){
                if ((x == 2 && y ==0) || (x == 3 && y ==4)){
                    continue;
                }
                else {
                    assertFalse(map.isOccupied(new Vector2d(x, y)));
                }
            }
        }
    }

    @Test
    void rectangularMapMovingAnimalsIllegalPosition(){
        OptionsParser op = new OptionsParser();
        MoveDirection[] moves = op.parse(List.of("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f",
                "f", "f", "f", "f", "f"));
        Vector2d[] initialPositions = {new Vector2d(2, 2), new Vector2d(2, 3), new Vector2d(2, 2)};
        RectangularMap map = new RectangularMap(10, 5, boundary);

        assertThrows(IllegalArgumentException.class, () -> {
            SimulationEngine engine = new SimulationEngine(moves, map, initialPositions, boundary);
        });
    }

    @Test
    void grassFieldCreatingAndPlacingAnimals(){
        MoveDirection[] moves = {MoveDirection.RIGHT, MoveDirection.BACKWARD};
        Vector2d[] initialPositions = {new Vector2d(1, 2), new Vector2d(2, 2), new Vector2d(2, 0)};
        GrassField map = new GrassField(303, boundary);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions, boundary);

        assertTrue(map.isOccupied(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
    }

    @Test
    void grassFieldMovingAnimals1(){
        MoveDirection[] moves = {MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD};
        Vector2d[] initialPositions = {new Vector2d(1, 2), new Vector2d(2, 1), new Vector2d(1, 0)};
        GrassField map = new GrassField(11, boundary);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions, boundary);

        engine.run();

        assertTrue(map.isOccupied(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(1, 1)));
    }

    @Test
    void grassFieldMovingAnimals2(){
        OptionsParser op = new OptionsParser();
        MoveDirection[] moves = op.parse(List.of("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f",
                "f", "f", "f", "f", "f"));
        Vector2d[] initialPositions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        GrassField map = new GrassField(19, boundary);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions, boundary);

        engine.run();

        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 7)));
        assertEquals(((Animal)map.objectAt(new Vector2d(2, 0))).getDirection(), MapDirection.SOUTH);
        assertEquals(((Animal)map.objectAt(new Vector2d(3, 7))).getDirection(), MapDirection.NORTH);

    }

    @Test
    void grassFieldMovingAnimalsIllegalPosition(){
        OptionsParser op = new OptionsParser();
        MoveDirection[] moves = op.parse(List.of("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f",
                "f", "f", "f", "f", "f"));
        Vector2d[] initialPositions = {new Vector2d(2, 2), new Vector2d(2, 3), new Vector2d(2, 2)};
        GrassField map = new GrassField(10, boundary);

        assertThrows(IllegalArgumentException.class, () -> {
            SimulationEngine engine = new SimulationEngine(moves, map, initialPositions, boundary);
        });
    }
}
