package agh.ics.oop;

public class Grass implements IMapElement {
    Vector2d position;

    public Grass(Vector2d initialPosition){
        this.position = initialPosition;
    }

    @Override
    public String getImageUrl() {
        return "src/main/resources/grass.png";
    }

    @Override
    public String toString(){
        return "*";
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public boolean isAt(Vector2d otherPosition){
        return this.position.equals(otherPosition);
    }
}
