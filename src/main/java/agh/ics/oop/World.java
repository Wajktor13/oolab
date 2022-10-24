package agh.ics.oop;
import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.System.out;


public class World {
    public static void main(String[] args){
//        ./gradlew.bat run --args="f l x backward e r"

        out.println("system has started\n");

        Animal newAnimal = new Animal();
        OptionParser op = new OptionParser();

        MoveDirection[] commands = op.parse(args);

        for (MoveDirection command : commands){
            newAnimal.move(command);
        }

        out.println(Arrays.toString(commands));
        out.println(newAnimal);

        out.println("\nsystem has stopped");
    }
}
