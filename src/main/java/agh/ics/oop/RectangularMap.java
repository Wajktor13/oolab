package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap{
    private final Vector2d leftBottomCorner = new Vector2d(0, 0), rightUpperCorner;
    private final MapBoundary boundary;


    public RectangularMap(int width, int height, MapBoundary boundary){
        this.boundary = boundary;
        this.rightUpperCorner = new Vector2d(width - 1, height - 1);
    }

    @Override
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition){
        animalsHashMap.remove(oldPosition);
        animalsHashMap.put(newPosition, animal);
    }

    @Override
    protected Vector2d[] findMinOccupiedMapCorners(){
        return new Vector2d[] {boundary.getLowerLeftCorner(), boundary.getUpperRightCorner()};
    }

    public boolean isValid(Vector2d position){
        return leftBottomCorner.precedes(position) && rightUpperCorner.follows(position);
    }

    @Override
    public Object objectAt(Vector2d position){
        if(isValid(position)){
            if (animalsHashMap.get(position) != null){

                return animalsHashMap.get(position);
            }
        }

        return null;
    }
}
