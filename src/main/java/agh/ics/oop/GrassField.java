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

    public GrassField(int numberOfGrass, MapBoundary boundary){
        this.numberOfGrass = numberOfGrass;
        this.grassRightUpperCorner = new Vector2d((int)Math.sqrt(numberOfGrass * 10),
                                                  (int)Math.sqrt(numberOfGrass * 10));
        this.boundary = boundary;

        placeGrass();
    }

    protected Vector2d[] findMinOccupiedMapCorners(){
            return new Vector2d[] {boundary.getLowerLeftCorner(), boundary.getUpperRightCorner()};
    }

    private void placeGrass(){
        ArrayList<Vector2d> availablePositions = new ArrayList<Vector2d>();

        for (int x = this.grassLeftBottomCorner.x; x <= this.grassRightUpperCorner.x; x++){
            for (int y = this.grassLeftBottomCorner.y; y <= this.grassRightUpperCorner.y; y++){
                availablePositions.add(new Vector2d(x, y));
            }
        }

        int n = availablePositions.size();

        for (int i = 0; i < this.numberOfGrass; i++){
            int randomInt = (int) (Math.random() * (n));
            Vector2d grassPosition = availablePositions.get(randomInt);
            grassHashMap.put(grassPosition, new Grass(grassPosition));
            boundary.addAwme(grassHashMap.get(grassPosition));
            availablePositions.remove(randomInt);
            n -= 1;
        }

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
