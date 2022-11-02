package agh.ics.oop;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class SimulationEngineTest {

    @Test
    void creatingAndPlacingAnimals(){
        MoveDirection[] moves = {MoveDirection.RIGHT, MoveDirection.BACKWARD};
        Vector2d[] initialPositions = {new Vector2d(1, 2), new Vector2d(2, 2), new Vector2d(2, 0)};
        RectangularMap map = new RectangularMap(3, 3);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions);

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
    void movingAnimals(){
        MoveDirection[] moves = {MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD};
        Vector2d[] initialPositions = {new Vector2d(1, 2), new Vector2d(2, 1), new Vector2d(1, 0)};
        RectangularMap map = new RectangularMap(3, 3);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions);

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
}
