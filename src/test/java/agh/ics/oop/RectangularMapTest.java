package agh.ics.oop;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class RectangularMapTest {
    private MapBoundary boundary;

    @BeforeEach
    void setup(){
        this.boundary = new MapBoundary();
    }

    @Test
    void isValid(){
        RectangularMap map = new RectangularMap(5, 5, boundary);

        assertTrue(map.isValid(new Vector2d(3, 0)));
        assertTrue(map.isValid(new Vector2d(1, 2)));
        assertTrue(map.isValid(new Vector2d(3, 4)));
        assertFalse(map.isValid(new Vector2d(3, 5)));
        assertFalse(map.isValid(new Vector2d(-1, 3)));
        assertFalse(map.isValid(new Vector2d(-12, 15)));

        map = new RectangularMap(10, 15, boundary);

        assertTrue(map.isValid(new Vector2d(9, 0)));
        assertTrue(map.isValid(new Vector2d(2, 8)));
        assertTrue(map.isValid(new Vector2d(1, 5)));
        assertFalse(map.isValid(new Vector2d(-1, 3)));
        assertFalse(map.isValid(new Vector2d(2, 113)));
        assertFalse(map.isValid(new Vector2d(-12, 23)));
    }

    @Test
    void canMoveToAndPlace(){
        RectangularMap map = new RectangularMap(6, 6, boundary);
        map.place(new Animal(map, new Vector2d(0, 3), boundary));
        map.place(new Animal(map, new Vector2d(4, 1), boundary));

        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertTrue(map.canMoveTo(new Vector2d(4, 2)));
        assertFalse(map.canMoveTo(new Vector2d(0, 3)));
        assertFalse(map.canMoveTo(new Vector2d(4, 1)));
        assertFalse(map.canMoveTo(new Vector2d(6, 1)));
        assertFalse(map.canMoveTo(new Vector2d(-2, 12)));


        map = new RectangularMap(20, 25, boundary);
        map.place(new Animal(map, new Vector2d(15, 8), boundary));
        map.place(new Animal(map, new Vector2d(9, 2), boundary));

        assertTrue(map.canMoveTo(new Vector2d(1, 12)));
        assertTrue(map.canMoveTo(new Vector2d(8, 7)));
        assertFalse(map.canMoveTo(new Vector2d(15, 8)));
        assertFalse(map.canMoveTo(new Vector2d(9, 2)));
        assertFalse(map.canMoveTo(new Vector2d(49, 1)));
        assertFalse(map.canMoveTo(new Vector2d(-2, 150)));
    }

    @Test
    void isOccupiedAndPlace(){
        RectangularMap map = new RectangularMap(8, 8, boundary);
        map.place(new Animal(map, new Vector2d(2, 3), boundary));
        map.place(new Animal(map, new Vector2d(4, 4), boundary));

        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertTrue(map.isOccupied(new Vector2d(4, 4)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(1, 4)));
        assertFalse(map.isOccupied(new Vector2d(10, 1)));
        assertFalse(map.isOccupied(new Vector2d(-1, 8)));


        map = new RectangularMap(15, 10, boundary);
        map.place(new Animal(map, new Vector2d(8, 9), boundary));
        map.place(new Animal(map, new Vector2d(1, 7), boundary));

        assertTrue(map.isOccupied(new Vector2d(8, 9)));
        assertTrue(map.isOccupied(new Vector2d(1, 7)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(11, 11)));
        assertFalse(map.isOccupied(new Vector2d(100, 1)));
        assertFalse(map.isOccupied(new Vector2d(-15, 8)));
    }

    @Test
    void objectAtAndPlace(){
        RectangularMap map = new RectangularMap(3, 3, boundary);
        Animal animal1 = new Animal(map, new Vector2d(1, 1), boundary);
        map.place(animal1);
        Animal animal2 = new Animal(map, new Vector2d(2, 0), boundary);
        map.place(animal2);

        assertEquals((Animal) map.objectAt(new Vector2d(1, 1)), animal1);
        assertEquals((Animal) map.objectAt(new Vector2d(2, 0)), animal2);
        assertNotEquals((Animal) map.objectAt(new Vector2d(2, 0)), animal1);
        assertNull(map.objectAt(new Vector2d(2, 1)));


        map = new RectangularMap(10, 15, boundary);
        animal1 = new Animal(map, new Vector2d(9, 11), boundary);
        map.place(animal1);
        animal2 = new Animal(map, new Vector2d(8, 2), boundary);
        map.place(animal2);

        assertEquals((Animal) map.objectAt(new Vector2d(9, 11)), animal1);
        assertEquals((Animal) map.objectAt(new Vector2d(8, 2)), animal2);
        assertNotEquals((Animal) map.objectAt(new Vector2d(8, 2)), animal1);
        assertNull(map.objectAt(new Vector2d(2, 11)));
    }
}
