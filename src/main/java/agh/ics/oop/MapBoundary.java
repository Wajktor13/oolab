package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private final SortedSet<Vector2d> xSet = new TreeSet<>((v1, v2) -> {
        if (v1.x > v2.x) return 1;
        else if (v1.x < v2.x) return -1;
        else {
            if (v1.y > v2.y) return 1;
            else if (v1.y < v2.y) return -1;
            else return 0;
        }
    });

    private final SortedSet<Vector2d> ySet = new TreeSet<>((v1, v2) -> {
        if (v1.y > v2.y) return 1;
        else if (v1.y < v2.y) return -1;
        else {
            if (v1.x > v2.x) return 1;
            else if (v1.x < v2.x) return -1;
            else return 0;
        }
    });

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        removeVector(oldPosition);
        addVector(newPosition);
    }

    public void addVector(Vector2d v){
        this.xSet.add(v);
        this.ySet.add(v);
    }

    public void removeVector(Vector2d v){
        this.xSet.remove(v);
        this.ySet.remove(v);
    }

    public Vector2d getLowerLeftCorner() {
        if (this.xSet.isEmpty()){
            return new Vector2d(0, 0);
        } else {
            return new Vector2d(this.xSet.first().x, this.ySet.first().y);
        }

    }

    public Vector2d getUpperRightCorner() {
        if (this.xSet.isEmpty()){
            return new Vector2d(0, 0);
        } else {
            return new Vector2d(this.xSet.last().x, this.ySet.last().y);
        }
    }
}
