package agh.ics.oop;


public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position;
    private final IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
    }

    @Override
    public String toString(){
        return this.direction.toString();
    }

    public boolean isAt(Vector2d otherPosition){
        return this.position.equals(otherPosition);
    }

    public void move(MoveDirection newDirection){
        Vector2d newPosition = this.position;

        switch (newDirection) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> newPosition = this.position.add(direction.toUnitVector());
            case BACKWARD -> newPosition = this.position.subtract(direction.toUnitVector());
        }

        if (map.canMoveTo(newPosition)){
            this.position = newPosition;
        }
    }

    public MapDirection getDirection(){
        return this.direction;
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
