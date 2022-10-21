package agh.ics.oop;
import static java.lang.System.out;


public class World {
    public static void main(String[] args){
        Animal newAnimal = new Animal();
        out.println(newAnimal);
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
