package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {

    @Test
    void parseOutput(){
        OptionsParser op = new OptionsParser();

        assertArrayEquals((new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD}),
                           op.parse(new String[]{"r", "left", "x", "x", "f"}));

        assertArrayEquals((new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                           MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD}),
                           op.parse(new String[]{"forward", "x", "f", "y", "b", "right", "ee", "left", "x", "b"}));

        assertArrayEquals((new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                           MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT,
                           MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT}),
                           op.parse(new String[]{"x", "r", "eree", "forward", "ff", "f", "left", "b", "left", "x",
                                                 "left", "b", "mnnh", "mnmnh", "l", "r", "ddssf"}));
    }
}
