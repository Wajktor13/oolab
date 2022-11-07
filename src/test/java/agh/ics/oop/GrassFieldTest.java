package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GrassFieldTest {

    @Test
    void canMoveToAndPlace(){
        GrassField map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(0, 3)));
        map.place(new Animal(map, new Vector2d(4, 1)));

        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertTrue(map.canMoveTo(new Vector2d(4, 2)));
        assertFalse(map.canMoveTo(new Vector2d(0, 3)));
        assertFalse(map.canMoveTo(new Vector2d(4, 1)));
        assertFalse(map.canMoveTo(new Vector2d(-2, 12)));


        map = new GrassField(222);
        map.place(new Animal(map, new Vector2d(15, 8)));
        map.place(new Animal(map, new Vector2d(9, 2)));

        assertTrue(map.canMoveTo(new Vector2d(1, 12)));
        assertTrue(map.canMoveTo(new Vector2d(8, 7)));
        assertFalse(map.canMoveTo(new Vector2d(15, 8)));
        assertFalse(map.canMoveTo(new Vector2d(9, 2)));
        assertFalse(map.canMoveTo(new Vector2d(-2, 150)));
    }

    @Test
    void isOccupiedAndPlace(){
        GrassField map = new GrassField(11);
        map.place(new Animal(map, new Vector2d(2, 3)));
        map.place(new Animal(map, new Vector2d(4, 14)));

        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertTrue(map.isOccupied(new Vector2d(4, 14)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(1, 4)));
        assertFalse(map.isOccupied(new Vector2d(10, 1)));
        assertFalse(map.isOccupied(new Vector2d(-1, 8)));


        map = new GrassField(153);
        map.place(new Animal(map, new Vector2d(8, 9)));
        map.place(new Animal(map, new Vector2d(1, 7)));

        assertTrue(map.isOccupied(new Vector2d(8, 9)));
        assertTrue(map.isOccupied(new Vector2d(1, 7)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(11, 11)));
        assertFalse(map.isOccupied(new Vector2d(100, 1)));
        assertFalse(map.isOccupied(new Vector2d(-15, 8)));
    }

    @Test
    void objectAtAndPlace(){
        GrassField map = new GrassField(33);
        Animal animal1 = new Animal(map, new Vector2d(12, 100));
        map.place(animal1);
        Animal animal2 = new Animal(map, new Vector2d(2, 0));
        map.place(animal2);

        assertEquals((Animal) map.objectAt(new Vector2d(12, 100)), animal1);
        assertEquals((Animal) map.objectAt(new Vector2d(2, 0)), animal2);
        assertNotEquals((Animal) map.objectAt(new Vector2d(2, 0)), animal1);
        assertFalse(map.objectAt(new Vector2d(2, 11)) instanceof Animal);


        map = new GrassField(115);
        animal1 = new Animal(map, new Vector2d(9, 11));
        map.place(animal1);
        animal2 = new Animal(map, new Vector2d(8, 2));
        map.place(animal2);

        assertEquals((Animal) map.objectAt(new Vector2d(9, 11)), animal1);
        assertEquals((Animal) map.objectAt(new Vector2d(8, 2)), animal2);
        assertNotEquals((Animal) map.objectAt(new Vector2d(8, 2)), animal1);
        assertFalse(map.objectAt(new Vector2d(2, 11)) instanceof Animal);
    }
}
