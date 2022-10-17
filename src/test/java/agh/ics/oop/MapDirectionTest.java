package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MapDirectionTest {

    @Test
    void nextNorth(){
        MapDirection direction = MapDirection.NORTH;

        assertEquals(MapDirection.EAST, direction.next());
    }

    @Test
    void nextEast(){
        MapDirection direction = MapDirection.EAST;

        assertEquals(MapDirection.SOUTH, direction.next());
    }

    @Test
    void nextSouth(){
        MapDirection direction = MapDirection.SOUTH;

        assertEquals(MapDirection.WEST, direction.next());
    }

    @Test
    void nextWest(){
        MapDirection direction = MapDirection.WEST;

        assertEquals(MapDirection.NORTH, direction.next());
    }

    @Test
    void previousNorth(){
        MapDirection direction = MapDirection.NORTH;

        assertEquals(MapDirection.WEST, direction.previous());
    }

    @Test
    void previousEast(){
        MapDirection direction = MapDirection.EAST;

        assertEquals(MapDirection.NORTH, direction.previous());
    }

    @Test
    void previousSouth(){
        MapDirection direction = MapDirection.SOUTH;

        assertEquals(MapDirection.EAST, direction.previous());
    }

    @Test
    void previousWest(){
        MapDirection direction = MapDirection.WEST;

        assertEquals(MapDirection.SOUTH, direction.previous());
    }
}
