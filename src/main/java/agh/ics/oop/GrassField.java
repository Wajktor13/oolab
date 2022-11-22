package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GrassField extends AbstractWorldMap{
    private final int numberOfGrass;
    private final Vector2d grassLeftBottomCorner = new Vector2d(0, 0);
    private final Vector2d grassRightUpperCorner;
    private final Vector2d animalLeftBottomCorner = new Vector2d(0, 0);
    private final Vector2d animalRightUpperCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private final Map<Vector2d, Grass> grassHashMap = new HashMap<>();
    private final MapBoundary boundary;
    private final ArrayList<Vector2d> freePositions = new ArrayList<Vector2d>();


    public GrassField(int numberOfGrass, MapBoundary boundary){
        this.numberOfGrass = numberOfGrass;
        this.grassRightUpperCorner = new Vector2d((int)Math.sqrt(numberOfGrass * 10),
                                                  (int)Math.sqrt(numberOfGrass * 10));
        this.boundary = boundary;


        setFreePositions();
        placeGrass();
    }

    private void setFreePositions(){
        for (int x = this.grassLeftBottomCorner.x; x <= this.grassRightUpperCorner.x; x++){
            for (int y = this.grassLeftBottomCorner.y; y <= this.grassRightUpperCorner.y; y++){
                freePositions.add(new Vector2d(x, y));
            }
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        if (objectAt(newPosition) instanceof Grass) respawnGrass(newPosition);

        Animal animal = animalsHashMap.remove(oldPosition);
        animalsHashMap.put(newPosition, animal);
        if (oldPosition.precedes(grassRightUpperCorner) && oldPosition.follows(grassLeftBottomCorner)){
            freePositions.add(oldPosition);
        }
        freePositions.remove(newPosition);
    }

    protected Vector2d[] findMinOccupiedMapCorners(){
            return new Vector2d[] {boundary.getLowerLeftCorner(), boundary.getUpperRightCorner()};
    }

    private void placeGrass(){
        int n = freePositions.size();

        for (int i = 0; i < this.numberOfGrass; i++){
            int randomInt = (int) (Math.random() * (n));
            Vector2d grassPosition = freePositions.get(randomInt);
            grassHashMap.put(grassPosition, new Grass(grassPosition));
            boundary.addVector(grassHashMap.get(grassPosition).position);
            freePositions.remove(grassPosition);
            n -= 1;
        }
    }

    private void respawnGrass(Vector2d oldPosition) {
        grassHashMap.remove(oldPosition);
        int randomInt = (int) (Math.random() * (freePositions.size()));
        Vector2d grassPosition = freePositions.get(randomInt);
        grassHashMap.put(grassPosition, new Grass(grassPosition));
        boundary.addVector(grassHashMap.get(grassPosition).position);
        freePositions.remove(grassPosition);
    }

    protected boolean isValid(Vector2d position){
        return animalLeftBottomCorner.precedes(position) && animalRightUpperCorner.follows(position);
    }

    @Override
    public Object objectAt(Vector2d position){
        if(isValid(position)){
            if (animalsHashMap.get(position) != null){
                return animalsHashMap.get(position);
            }

            if (grassHashMap.get(position) != null){
                return grassHashMap.get(position);
            }

        }

        return null;
    }
}
