package agh.ics.oop;


import static java.lang.System.out;


public class World {

    //        ./gradlew.bat run --args="f b r l f f r r f f f f f f f f"

    public static void main(String[] args){
        try {
            out.println("system has started\n");

            MoveDirection[] directions = new OptionsParser().parse(args);
            MapBoundary boundary = new MapBoundary();
            IWorldMap map = new GrassField(10, boundary);
            Vector2d[] positions = { new Vector2d(0,3), new Vector2d(6,8) };
            IEngine engine = new SimulationEngine(directions, map, positions, boundary);
            engine.run();
            out.println(map);

        } catch (IllegalArgumentException ex){
            out.println(ex.getMessage());

        } finally {
            out.println("\nsystem has stopped");
        }

    }
}
