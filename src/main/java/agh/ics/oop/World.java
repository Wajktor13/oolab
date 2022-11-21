package agh.ics.oop;


import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.out;


public class World {

    //        ./gradlew.bat run --args="f b r l f f r r f f f f f f f f"

    public static void main(String[] args){
        out.println("system has started\n");

        Application.launch(App.class, args);

        out.println("\nsystem has stopped");
    }
}
