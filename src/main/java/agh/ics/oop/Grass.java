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

    public boolean isAt(Vector2d otherPosition){
        return this.position.equals(otherPosition);
    }
}
