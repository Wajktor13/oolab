package agh.ics.oop;

import java.util.ArrayList;


public class GrassField extends AbstractWorldMap{
    private final int numberOfGrass;
    private final Vector2d grassLeftBottomCorner = new Vector2d(0, 0);
    private final Vector2d grassRightUpperCorner;
    private final Vector2d animalLeftBottomCorner = new Vector2d(0, 0);
    private final Vector2d animalRightUpperCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private final ArrayList<Grass> grassList = new ArrayList<>();

    public GrassField(int numberOfGrass){
        this.numberOfGrass = numberOfGrass;
        this.grassRightUpperCorner = new Vector2d((int)Math.sqrt(numberOfGrass * 10),
                                                  (int)Math.sqrt(numberOfGrass * 10));

        placeGrass();
    }

    protected Vector2d[] findMinOccupiedMapCorners(){

        if (animalsList.isEmpty()){
            return new Vector2d[] {new Vector2d(0, 0), new Vector2d(0, 0)};
        }

        else {
            int lowestX = animalRightUpperCorner.x, highestX = animalLeftBottomCorner.x;
            int lowestY = animalRightUpperCorner.y, highestY = animalLeftBottomCorner.y;

            for (Animal animal : this.animalsList) {
                lowestX = Math.min(lowestX, animal.getPosition().x);
                highestX = Math.max(highestX, animal.getPosition().x);
                lowestY = Math.min(lowestY, animal.getPosition().y);
                highestY = Math.max(highestY, animal.getPosition().y);
            }

            for (Grass grass : this.grassList) {
                lowestX = Math.min(lowestX, grass.getPosition().x);
                highestX = Math.max(highestX, grass.getPosition().x);
                lowestY = Math.min(lowestY, grass.getPosition().y);
                highestY = Math.max(highestY, grass.getPosition().y);
            }

            return new Vector2d[] {new Vector2d(lowestX, lowestY), new Vector2d(highestX, highestY)};
        }

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

            grassList.add(new Grass(availablePositions.get(randomInt)));
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
            for (Animal animal : animalsList){
                if (animal.isAt(position)){

                    return animal;
                }
            }

            for (Grass grass : grassList) {
                if (grass.isAt(position)) {

                    return grass;
                }
            }
        }

        return null;
    }
}
