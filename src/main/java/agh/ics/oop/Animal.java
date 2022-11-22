package agh.ics.oop;


import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement {
    private MapDirection direction = MapDirection.NORTH;
    private final IWorldMap map;
    private final MapBoundary boundary;
    private final ArrayList<IPositionChangeObserver> observers = new ArrayList<>();


    public Animal(IWorldMap map, Vector2d initialPosition, MapBoundary boundary){
        this.map = map;
        this.boundary = boundary;
        addObserver((IPositionChangeObserver) map);
        addObserver(boundary);
        this.position = initialPosition;
    }

    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    @Override
    public String toString(){
        return this.direction.toString();
    }

    public void move(MoveDirection newDirection){
        Vector2d newPosition = this.position;
        Vector2d oldPosition = this.position;

        switch (newDirection) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = this.position.add(direction.toUnitVector());
            case BACKWARD -> newPosition = this.position.subtract(direction.toUnitVector());
        }

        if (newPosition != this.position && map.canMoveTo(newPosition)){
            this.position = newPosition;
            this.positionChanged(oldPosition, newPosition);
        }
    }

    public MapDirection getDirection(){
        return this.direction;
    }
}
