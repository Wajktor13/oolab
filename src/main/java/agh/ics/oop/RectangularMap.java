package agh.ics.oop;

import java.util.ArrayList;


public class RectangularMap implements IWorldMap{
    private final Vector2d leftBottomCorner = new Vector2d(0, 0), rightUpperCorner;
    private final ArrayList<Animal> animalsList = new ArrayList<>();

    public RectangularMap(int width, int height){
        this.rightUpperCorner = new Vector2d(width - 1, height - 1);
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);

        return  mapVisualizer.draw(leftBottomCorner, rightUpperCorner);
    }


    public boolean isValid(Vector2d position){
        return leftBottomCorner.precedes(position) && rightUpperCorner.follows(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return isValid(position) && !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        if(isValid(position)){
            return objectAt(position) != null;
        }

        return false; // error?
    }

    @Override
    public Object objectAt(Vector2d position){
        if(isValid(position)){
            for (Animal animal : animalsList){
                if (position.equals(animal.getPosition())){

                    return animal;
                }
            }
        }

        return null;
    }

    @Override
    public boolean place(Animal animal){
        Vector2d position = animal.getPosition();
        if (!canMoveTo(position)){

            return false;
        }
        else {
            animalsList.add(animal);

            return true;
        }
    }
}
