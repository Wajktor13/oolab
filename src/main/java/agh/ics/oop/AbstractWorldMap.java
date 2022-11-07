package agh.ics.oop;

import java.util.ArrayList;

abstract class AbstractWorldMap implements IWorldMap{
    protected final ArrayList<Animal> animalsList = new ArrayList<>();


    protected abstract Vector2d[] findMinOccupiedMapCorners();

    protected abstract boolean isValid(Vector2d position);

    @Override
    public abstract Object objectAt(Vector2d position);

    @Override
    public String toString(){
        MapVisualizer mapVisualizer = new MapVisualizer(this);

        Vector2d[] corners = findMinOccupiedMapCorners();

        return  mapVisualizer.draw(corners[0], corners[1]);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return isValid(position) && !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        if(isValid(position)){
            return objectAt(position) instanceof Animal;
        }

        return false;
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
