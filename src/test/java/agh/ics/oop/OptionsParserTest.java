package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {

    @Test
    void parseOutputLegalCommands(){
        OptionsParser op = new OptionsParser();

        assertArrayEquals((new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD}),
                           op.parse(new String[]{"r", "left", "f"}));

        assertArrayEquals((new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                           MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD}),
                           op.parse(new String[]{"forward", "f", "b", "right", "left", "backward"}));

        assertArrayEquals((new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                           MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT,
                           MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT}),
                           op.parse(new String[]{"r", "forward", "f", "left", "b", "left", "left", "b", "l", "r"}));
    }

    @Test
    void parseOutputIllegalCommands(){
        OptionsParser op = new OptionsParser();

        assertThrows(IllegalArgumentException.class, () -> {
            op.parse(new String[]{"r", "x", "backward"});
        });

        assertThrows(IllegalArgumentException.class, () -> {
            op.parse(new String[]{"left", "r", "backward", "f", "ax", "ew22"});
        });

        assertThrows(IllegalArgumentException.class, () -> {
            op.parse(new String[]{"left", "gg", "backward", "f", "r", "r", "forward", "f", "b", "right"});
        });
    }
}
