package agh.ics.oop;

import java.util.Objects;

abstract class AbstractWorldMapElement {
    protected Vector2d position;

    public abstract String toString();

    public Vector2d getPosition(){
        return this.position;
    }

    public boolean isAt(Vector2d otherPosition){
        return this.position.equals(otherPosition);
    }

    @Override
    public int hashCode() {
        return  Objects.hash(this.position.x, this.position.y);
    }
}
