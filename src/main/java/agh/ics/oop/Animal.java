package agh.ics.oop;

public class Animal {
    public MapDirection direction = MapDirection.NORTH;
    public Vector2d position = new Vector2d(2, 2);

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

        if ((new Vector2d(0, 0)).precedes(newPosition) && (new Vector2d(4, 4)).follows(newPosition)){
            this.position = newPosition;
        }
    }

    public static void main(String[] args) {
        Animal nww = new Animal();
        nww.move(MoveDirection.BACKWARD);
    }
}
