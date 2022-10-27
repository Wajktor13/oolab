package agh.ics.oop;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private final int[][] corners = {{0, 0}, {4, 4}};

    public String toString(){
        return "Position: " + position + "\nDirection: " + direction;
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

        if ((new Vector2d(corners[0][0], corners[0][1])).precedes(newPosition) &&
                (new Vector2d(corners[1][0], corners[1][1])).follows(newPosition)){
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
