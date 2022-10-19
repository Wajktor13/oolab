package agh.ics.oop;
import static java.lang.System.out;


public class World {
    public static void main(String[] args){
        out.println("system wystartował");

        Direction[] convertedCommands = convert(args);
        run(convertedCommands);

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection direction1 = MapDirection.NORTH;
        out.println(direction1);
        out.println(direction1.toUnitVector());
        out.println(direction1.next());
        out.println(direction1.previous());

        out.println("system zakończył działanie");
    }

    public static Direction[] convert(String[] commands){
        int n = commands.length;
        int i = 0;
        Direction[] convertedCommands = new Direction[n];

        for (String command : commands){
            convertedCommands[i] = switch (command) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.UNDEFINED;
            };

            i++;
        }

        return convertedCommands;
    }

    public static void run(Direction[] commands) {
        for (Direction command : commands) {
            switch (command) {
                case FORWARD -> out.println("zwierzak idzie do przodu");
                case BACKWARD -> out.println("zwierzak idzie do tyłu");
                case RIGHT -> out.println("zwierzak skręca w prawo");
                case LEFT -> out.println("zwierzak skręca w lewo");
            }
        }
    }
}
