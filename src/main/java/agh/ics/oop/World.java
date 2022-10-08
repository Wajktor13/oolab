package agh.ics.oop;
import static java.lang.System.out;

public class World {
    public static void main(String[] args){
        out.println("system wystartował");
//        run1();
//        run2();
        run3(args);
        out.println("system zakończył działanie");
    }

    public static void run1(){
        out.println("zwierzak idzie do przodu");

    }

    public static void run2(String[] args){
        out.println("zwierzak idzie do przodu");

        int n = args.length;

        if (n > 0){
            out.print(args[0]);

            for (int i = 1; i < n; i++){
                out.print(", " + args[i]);
            }

            out.println();
        }
    }

    public static void run3(String[] args){
        out.println("zwierzak idzie do przodu");

        for(String arg : args){
            switch (arg) {
                case "f" -> out.println("zwierzak idzie do przodu");
                case "b" -> out.println("zwierzak idzie do tyłu");
                case "r" -> out.println("zwierzak skręca w prawo");
                case "l" -> out.println("zwierzak skręca w lewo");
            }
        }
    }


}
