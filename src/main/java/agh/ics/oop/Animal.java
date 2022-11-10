package agh.ics.oop;


public class Animal extends AbstractWorldMapElement {
    private MapDirection direction = MapDirection.NORTH;
    private final IWorldMap map;

    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
    }

    @Override
    public String toString(){
        return this.direction.toString();
    }

    public void move(MoveDirection newDirection){
        Vector2d newPosition = this.position;

        switch (newDirection) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = this.position.add(direction.toUnitVector());
            case BACKWARD -> newPosition = this.position.subtract(direction.toUnitVector());
        }

        if (newPosition != this.position && map.canMoveTo(newPosition)){
            this.position = newPosition;
        }
    }

    public MapDirection getDirection(){
        return this.direction;
    }
}
