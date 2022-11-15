package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap{
    private final Vector2d leftBottomCorner = new Vector2d(0, 0), rightUpperCorner;


    @Override
    protected Vector2d[] findMinOccupiedMapCorners(){
        return new Vector2d[] {this.leftBottomCorner, this.rightUpperCorner};
    }

    public RectangularMap(int width, int height){
        this.rightUpperCorner = new Vector2d(width - 1, height - 1);
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
