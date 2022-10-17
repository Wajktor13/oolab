package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Vector2dTest {

    @Test
    void equalsSelf(){
        Vector2d vector = new Vector2d(0, 1);

        assertTrue(vector.equals(vector));
    }

    @Test
    void equalsOther1(){
        Vector2d vector1 = new Vector2d(-5, 6);
        Vector2d vector2 = new Vector2d(-5, 6);

        assertTrue(vector1.equals(vector2));
    }

    @Test
    void equalsOther2(){
        Vector2d vector1 = new Vector2d(100, 200);
        Vector2d vector2 = new Vector2d(101, 200);

        assertFalse(vector1.equals(vector2));
    }

    @Test
    void equalsOtherWrongClass(){
        Object vector1 = new Object();
        Vector2d vector2 = new Vector2d(-200, 100);

        assertFalse(vector1.equals(vector2));
    }

    @Test
    void toString1(){
        Vector2d vector1 = new Vector2d(23, 32);

        assertEquals("(23,32)", vector1.toString());
    }

    @Test
    void toString2(){
        Vector2d vector1 = new Vector2d(0, 1);

        assertEquals("(0,1)", vector1.toString());
    }

    @Test
    void precedesSelf(){
        Vector2d vector = new Vector2d(0, 1);

        assertTrue(vector.precedes(vector));
    }

    @Test
    void precedesOther1(){
        Vector2d vector1 = new Vector2d(5, 10);
        Vector2d vector2 = new Vector2d(-5, 1);

        assertTrue(vector2.precedes(vector1));
    }

    @Test
    void precedesOther2(){
        Vector2d vector1 = new Vector2d(1000, 10);
        Vector2d vector2 = new Vector2d(100, 500);

        assertFalse(vector2.precedes(vector1));
    }

    @Test
    void followsSelf(){
        Vector2d vector = new Vector2d(5, 10);

        assertTrue(vector.precedes(vector));
    }

    @Test
    void followsOther1(){
        Vector2d vector1 = new Vector2d(1, 7);
        Vector2d vector2 = new Vector2d(115, 22);

        assertTrue(vector2.follows(vector1));
    }

    @Test
    void followsOther2(){
        Vector2d vector1 = new Vector2d(50, 109);
        Vector2d vector2 = new Vector2d(1, 500);

        assertFalse(vector2.follows(vector1));
    }

    @Test
    void upperRightSelf(){
        Vector2d vector = new Vector2d(50, 109);

        assertEquals(vector, vector.upperRight(vector));
    }

    @Test
    void upperRightOther1(){
        Vector2d vector1 = new Vector2d(50, 109);
        Vector2d vector2 = new Vector2d(12, 201);

        assertEquals(vector1.upperRight(vector2), new Vector2d(50, 201));
    }

    @Test
    void upperRightOther2(){
        Vector2d vector1 = new Vector2d(11, 25010900);
        Vector2d vector2 = new Vector2d(12, 231);

        assertEquals(vector1.upperRight(vector2), new Vector2d(12, 25010900));
    }

    @Test
    void lowerLeftSelf(){
        Vector2d vector = new Vector2d(50, 109);

        assertEquals(vector, vector.lowerLeft(vector));
    }

    @Test
    void lowerLeftOther1(){
        Vector2d vector1 = new Vector2d(50, 109);
        Vector2d vector2 = new Vector2d(12, 201);

        assertEquals(vector1.lowerLeft(vector2), new Vector2d(12, 109));
    }

    @Test
    void lowerLeftOther2(){
        Vector2d vector1 = new Vector2d(-11, 25010900);
        Vector2d vector2 = new Vector2d(12, 231);

        assertEquals(vector1.lowerLeft(vector2), new Vector2d(-11, 231));
    }

    @Test
    void addSelf(){
        Vector2d vector = new Vector2d(1, 2);

        assertEquals(new Vector2d(2, 4), vector.add(vector));
    }

    @Test
    void addOther1(){
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector2 = new Vector2d(5, 10);

        assertEquals(new Vector2d(6, 12), vector1.add(vector2));
    }

    @Test
    void addOther2(){
        Vector2d vector1 = new Vector2d(11, 322);
        Vector2d vector2 = new Vector2d(50, 100);

        assertEquals(new Vector2d(61, 422), vector1.add(vector2));
    }

    @Test
    void subtractSelf(){
        Vector2d vector = new Vector2d(1, 2);

        assertEquals(new Vector2d(0, 0), vector.subtract(vector));
    }

    @Test
    void subtractOther1(){
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector2 = new Vector2d(5, 10);

        assertEquals(new Vector2d(-4, -8), vector1.subtract(vector2));
    }

    @Test
    void subtractOther2(){
        Vector2d vector1 = new Vector2d(11, 322);
        Vector2d vector2 = new Vector2d(50, 100);

        assertEquals(new Vector2d(-39, 222), vector1.subtract(vector2));
    }

    @Test
    void opposite1(){
        Vector2d vector = new Vector2d(11, 322);

        assertEquals(new Vector2d(-11, -322), vector.opposite());
    }

    @Test
    void opposite2(){
        Vector2d vector = new Vector2d(11, -13);

        assertEquals(new Vector2d(-11, 13), vector.opposite());
    }
}
