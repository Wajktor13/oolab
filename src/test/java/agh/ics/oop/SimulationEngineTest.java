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
    void movingAnimals1(){
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

    @Test
    void movingAnimals2(){
        OptionsParser op = new OptionsParser();
        MoveDirection[] moves = op.parse(new String[]{"f", "b", "r", "l", "f", "x", "f", "r", "r", "f", "f", "f",
                                                      "f", "f", "f","y", "f", "f"});
        Vector2d[] initialPositions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        RectangularMap map = new RectangularMap(10, 5);
        SimulationEngine engine = new SimulationEngine(moves, map, initialPositions);

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
}
