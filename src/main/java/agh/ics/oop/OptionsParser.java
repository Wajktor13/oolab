package agh.ics.oop;

public class OptionsParser {

    public MoveDirection[] parse(String[] commands) {
        int n = commands.length;
        int countUndefined = 0;
        int i = 0;

        for (String str : commands) {
            switch (str) {
                case "forward", "f", "backward", "b", "right", "r", "left", "l" -> {}
                default -> countUndefined++;
            }
        }

        MoveDirection[] convertedCommands = new MoveDirection[n - countUndefined];

        for (String command : commands) {
            switch (command) {
                case "forward", "f" -> convertedCommands[i++] = MoveDirection.FORWARD;
                case "backward", "b" -> convertedCommands[i++] = MoveDirection.BACKWARD;
                case "right", "r" -> convertedCommands[i++] = MoveDirection.RIGHT;
                case "left", "l" -> convertedCommands[i++] = MoveDirection.LEFT;
            }
        }

        return convertedCommands;
    }
}
