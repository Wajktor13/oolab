package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;


abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final Map<Vector2d, Animal> animalsHashMap = new HashMap<>();


    protected abstract Vector2d[] findMinOccupiedMapCorners();

    protected abstract boolean isValid(Vector2d position);

    @Override
    public abstract Object objectAt(Vector2d position);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animalsHashMap.get(oldPosition);
        animalsHashMap.remove(oldPosition);
        animalsHashMap.put(newPosition, animal);
    }

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
    public boolean place(Animal animal) throws IllegalArgumentException{
        Vector2d position = animal.getPosition();
        if (!canMoveTo(position)){

            throw new IllegalArgumentException(String.format("'%s' is either invalid or occupied", position));
        }
        else {
            animalsHashMap.put(animal.position, animal);

            return true;
        }
    }
}
