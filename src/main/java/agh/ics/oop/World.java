package agh.ics.oop;


import static java.lang.System.out;


public class World {

    //        ./gradlew.bat run --args="f b r l f f r r f f f f f f f f"

    public static void main(String[] args){
        try {
            out.println("system has started\n");

            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,1), new Vector2d(2,2) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            out.println(map);

        } catch (IllegalArgumentException ex){
            out.println(ex.getMessage());

        } finally {
            out.println("\nsystem has stopped");
        }

    }
}
