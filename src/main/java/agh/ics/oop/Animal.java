package agh.ics.oop;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    public String toString(){
        return "Pozycja: " + position + "\nOrientacja: " + direction;
    }

    public boolean isAt(Vector2d otherPosition){
        return this.position.equals(otherPosition);
    }

    public void move(MoveDirection newDirection){
        Vector2d newPosition;

        switch (newDirection){
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case FORWARD:
                newPosition = this.position.add(direction.toUnitVector());
                if (){

                }
                break;
            case BACKWARD:
                newPosition = this.position.subtract(direction.toUnitVector());
                if (){

                }
                break;
        }
    }
}
