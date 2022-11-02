package agh.ics.oop;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class AnimalTest {
    private Animal testAnimal;

    @BeforeEach
    void setup(){

        testAnimal = new Animal(new RectangularMap(5, 5), new Vector2d(2, 2));
    }

    @Test
    void moveDirection(){

        MoveDirection[] moveDir = new MoveDirection[] {MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.LEFT};

        MapDirection[] expectedDirection = new MapDirection[] {MapDirection.WEST, MapDirection.NORTH,
                MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.EAST, MapDirection.SOUTH,
                MapDirection.WEST, MapDirection.NORTH, MapDirection.NORTH, MapDirection.WEST, MapDirection.SOUTH};

        assertEquals(MapDirection.NORTH, testAnimal.getDirection());

        for (int i = 0; i < moveDir.length; i++){
            testAnimal.move(moveDir[i]);
            assertEquals(expectedDirection[i], testAnimal.getDirection());
        }
    }

    @Test
    void movePosition(){

        MoveDirection[] moveDir = new MoveDirection[] {MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD,
                MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD};

        int[][] expectedPosition = {{2, 3}, {2, 4}, {2, 4}, {2, 4}, {3, 4}, {4, 4}, {4, 4}, {3, 4}, {2, 4}, {1, 4},
                                    {0, 4}, {0, 4}, {0, 4}, {0, 4}, {0, 3}, {0, 2}, {0, 1}, {0, 0}, {0, 0}};

        assertEquals(new Vector2d(2, 2), testAnimal.getPosition());

        for (int i = 0; i < moveDir.length; i++){
            testAnimal.move(moveDir[i]);
            assertEquals(new Vector2d(expectedPosition[i][0], expectedPosition[i][1]), testAnimal.getPosition());
        }
    }

    @Test
    void moveStaysOnMap(){
        final int[][] corners = {{0, 0}, {4, 4}};
        int n = 8;

        MoveDirection[] moveDir = new MoveDirection[] {MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.RIGHT};

        for (MoveDirection md : moveDir){
            testAnimal.move(md);

            for (int i = 0; i < n; i++){
                testAnimal.move(MoveDirection.FORWARD);
            }

            assertTrue((new Vector2d(corners[1][0], corners[1][1]).follows(testAnimal.getPosition())) &&
                    (new Vector2d(corners[0][0], corners[0][1]).precedes(testAnimal.getPosition())));

            for (int i = 0; i < n; i++){
                testAnimal.move(MoveDirection.BACKWARD);
            }

            assertTrue((new Vector2d(corners[1][0], corners[1][1]).follows(testAnimal.getPosition())) &&
                    (new Vector2d(corners[0][0], corners[0][1]).precedes(testAnimal.getPosition())));
        }
    }
}
