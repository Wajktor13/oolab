package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    SortedSet<AbstractWorldMapElement> xSet = new TreeSet<>((awme1, awme2) -> {
        Vector2d awme1Vector2d = awme1.getPosition();
        Vector2d awme2Vector2d = awme2.getPosition();

        if (awme1Vector2d.x > awme2Vector2d.x) return 1;
        else if (awme1Vector2d.x < awme2Vector2d.x) return -1;
        else {
            if (awme1Vector2d.y > awme2Vector2d.y) return 1;
            else if (awme1Vector2d.y < awme2Vector2d.y) return -1;
            else {
                if (awme1 instanceof Animal && awme2 instanceof Animal) return 0;
                else if(awme1 instanceof Animal) return 1;
                else return -1;
            }
        }
    });

    SortedSet<AbstractWorldMapElement> ySet = new TreeSet<>((awme1, awme2) -> {
        Vector2d awme1Vector2d = awme1.getPosition();
        Vector2d awme2Vector2d = awme2.getPosition();

        if (awme1Vector2d.y > awme2Vector2d.y) return 1;
        else if (awme1Vector2d.y < awme2Vector2d.y) return -1;
        else {
            if (awme1Vector2d.x > awme2Vector2d.x) return 1;
            else if (awme1Vector2d.x < awme2Vector2d.x) return -1;
            else {
                if (awme1 instanceof Animal && awme2 instanceof Animal) return 0;
                else if(awme1 instanceof Animal) return 1;
                else return -1;
            }
        }
    });

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        AbstractWorldMapElement awmeToUpdate = null;
        for (AbstractWorldMapElement awme : xSet){
            if (awme.position.equals(newPosition)){
                awmeToUpdate = awme;
                break;
            }
        }

        if (awmeToUpdate != null){
            removeAwme(awmeToUpdate);
            addAwme(awmeToUpdate);
        }
    }

    public void addAwme(AbstractWorldMapElement awme){
        xSet.add(awme);
        ySet.add(awme);
    }

    public void removeAwme(AbstractWorldMapElement awme){
        xSet.remove(awme);
        ySet.remove(awme);
    }

    public Vector2d getLowerLeftCorner() {
        if (xSet.isEmpty()){
            return new Vector2d(0, 0);
        } else {
            return new Vector2d(xSet.first().position.x, ySet.first().position.y);
        }

    }

    public Vector2d getUpperRightCorner() {
        if (xSet.isEmpty()){
            return new Vector2d(0, 0);
        } else {
            return new Vector2d(xSet.last().position.x, ySet.last().position.y);
        }
    }
}
