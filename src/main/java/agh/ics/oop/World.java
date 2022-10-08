package agh.ics.oop;
import static java.lang.System.out;


public class World {

    public static void main(String[] args){
        out.println("system wystartował");

//        run1();
//        run2(args);
//        run3(args);
        Direction[] convertedCommands = convert(args);
        run4(convertedCommands);

        out.println("system zakończył działanie");
    }

    public static void run1(){
        out.println("zwierzak idzie do przodu");

    }

    public static void run2(String[] commands){
        out.println("zwierzak idzie do przodu");

        int n = commands.length;

        if (n > 0){
            out.print(commands[0]);

            for (int i = 1; i < n; i++){
                out.print(", " + commands[i]);
            }

            out.println();
        }
    }

    public static void run3(String[] commands){
        out.println("zwierzak idzie do przodu");

        for(String command : commands){
            switch (command) {
                case "f" -> out.println("zwierzak idzie do przodu");
                case "b" -> out.println("zwierzak idzie do tyłu");
                case "r" -> out.println("zwierzak skręca w prawo");
                case "l" -> out.println("zwierzak skręca w lewo");
            }
        }
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

    public static void run4(Direction[] commands) {
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
