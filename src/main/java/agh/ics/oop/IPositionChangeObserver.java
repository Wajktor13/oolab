package agh.ics.oop;

public interface IPositionChangeObserver {

    /**
     * Removes value with oldPosition keys and adds the same value with
     * newPosition key
     *
     * @param oldPosition
     *            key to be removed
     * @param newPosition
     *            key to be added
     */
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
