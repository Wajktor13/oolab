package agh.ics.oop;

public class Grass {
    private Vector2d position;

    public Grass(Vector2d initialPosition){
        this.position = initialPosition;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString(){
        return "*";
    }
}
