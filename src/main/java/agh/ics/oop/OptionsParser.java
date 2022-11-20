package agh.ics.oop;

public class OptionsParser{

    public MoveDirection[] parse(String[] commands) throws IllegalArgumentException{
        int n = commands.length;
        int i = 0;
        MoveDirection[] convertedCommands = new MoveDirection[n];

        for (String command : commands) {
            switch (command) {
                case "forward", "f" -> convertedCommands[i++] = MoveDirection.FORWARD;
                case "backward", "b" -> convertedCommands[i++] = MoveDirection.BACKWARD;
                case "right", "r" -> convertedCommands[i++] = MoveDirection.RIGHT;
                case "left", "l" -> convertedCommands[i++] = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(String.format("'%s' is not a legal move command",
                                                                            command));
            }
        }

        return convertedCommands;
    }
}
