package agh.ics.oop;


abstract class AbstractWorldMapElement{
    protected Vector2d position;

    public abstract String toString();

    public Vector2d getPosition(){
        return this.position;
    }

    public boolean isAt(Vector2d otherPosition){
        return this.position.equals(otherPosition);
    }
}
