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
                this.freePositions.add(new Vector2d(x, y));
            }
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        if (objectAt(newPosition) instanceof Grass) respawnGrass(newPosition);

        Animal animal = animalsHashMap.remove(oldPosition);
        animalsHashMap.put(newPosition, animal);
        if (oldPosition.precedes(this.grassRightUpperCorner) && oldPosition.follows(this.grassLeftBottomCorner)){
            this.freePositions.add(oldPosition);
        }
        this.freePositions.remove(newPosition);
    }

    protected Vector2d[] findMinOccupiedMapCorners(){
            return new Vector2d[] {this.boundary.getLowerLeftCorner(), this.boundary.getUpperRightCorner()};
    }

    private void placeGrass(){
        int n = this.freePositions.size();

        for (int i = 0; i < this.numberOfGrass; i++){
            int randomInt = (int) (Math.random() * (n));
            Vector2d grassPosition = this.freePositions.get(randomInt);
            this.grassHashMap.put(grassPosition, new Grass(grassPosition));
            this.boundary.addVector(this.grassHashMap.get(grassPosition).position);
            this.freePositions.remove(grassPosition);
            n -= 1;
        }
    }

    private void respawnGrass(Vector2d oldPosition) {
        this.grassHashMap.remove(oldPosition);
        int randomInt = (int) (Math.random() * (this.freePositions.size()));
        Vector2d grassPosition = this.freePositions.get(randomInt);
        this.grassHashMap.put(grassPosition, new Grass(grassPosition));
        this.boundary.addVector(this.grassHashMap.get(grassPosition).position);
        this.freePositions.remove(grassPosition);
    }

    protected boolean isValid(Vector2d position){
        return this.animalLeftBottomCorner.precedes(position) && this.animalRightUpperCorner.follows(position);
    }

    @Override
    public Object objectAt(Vector2d position){
        if(isValid(position)){
            if (animalsHashMap.get(position) != null){
                return animalsHashMap.get(position);
            }

            if (this.grassHashMap.get(position) != null){
                return this.grassHashMap.get(position);
            }
        }

        return null;
    }
}
