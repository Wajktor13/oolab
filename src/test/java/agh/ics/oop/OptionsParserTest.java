package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    private OptionsParser op;

    @BeforeEach
    void setup(){
        this.op = new OptionsParser();
    }

    @Test
    void parseOutputLegalCommands(){
        assertArrayEquals((new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD}),
                           op.parse(List.of(new String[]{"r", "left", "f"})));

        assertArrayEquals((new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                           MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD}),
                           op.parse(List.of(new String[]{"forward", "f", "b", "right", "left", "backward"})));

        assertArrayEquals((new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                           MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT,
                           MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT}),
                           op.parse(List.of(new String[]{"r", "forward", "f", "left", "b", "left", "left",
                                                         "b", "l", "r"})));
    }

    @Test
    void parseOutputIllegalCommands(){
        assertThrows(IllegalArgumentException.class, () -> {
            op.parse(List.of(new String[]{"r", "x", "backward"}));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            op.parse(List.of(new String[]{"left", "r", "backward", "f", "ax", "ew22"}));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            op.parse(List.of(new String[]{"left", "gg", "backward", "f", "r", "r", "forward", "f", "b", "right"}));
        });
    }
}
