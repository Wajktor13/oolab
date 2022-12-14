package agh.ics.oop;


import java.util.ArrayList;

public class Animal implements IMapElement {
    protected Vector2d position;
    private MapDirection direction = MapDirection.NORTH;
    private final IWorldMap map;
    private final ArrayList<IPositionChangeObserver> observers = new ArrayList<>();


    public Animal(IWorldMap map, Vector2d initialPosition, MapBoundary boundary){
        this.map = map;
        addObserver((IPositionChangeObserver) map);
        addObserver(boundary);
        this.position = initialPosition;
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : this.observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    @Override
    public String getImageUrl() {
        return switch (direction) {
            case NORTH -> "src/main/resources/animal_up.png";
            case SOUTH -> "src/main/resources/animal_down.png";
            case WEST -> "src/main/resources/animal_left.png";
            case EAST -> "src/main/resources/animal_right.png";
        };
    }

    @Override
    public String toString(){
        return "animal";
    }

    public void move(MoveDirection moveDirection){
        Vector2d newPosition = this.position;
        Vector2d oldPosition = this.position;
        MapDirection oldDirection = this.direction;

        switch (moveDirection) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = this.position.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = this.position.subtract(this.direction.toUnitVector());
        }

        if ((newPosition != this.position && this.map.canMoveTo(newPosition)) || oldDirection != this.direction){
            this.position = newPosition;
            this.positionChanged(oldPosition, newPosition);
        }
    }

    public MapDirection getDirection(){
        return this.direction;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public boolean isAt(Vector2d otherPosition){
        return this.position.equals(otherPosition);
    }
}
