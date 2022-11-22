package agh.ics.oop;

import java.util.ArrayList;


public class SimulationEngine implements IEngine{
    private final MoveDirection[] moves;
    private final IWorldMap map;
    private final ArrayList<Animal> animalsList = new ArrayList<>();
    private final MapBoundary boundary;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions, MapBoundary boundary){
        this.moves = moves;
        this.map = map;
        this.boundary = boundary;

        for (Vector2d position : initialPositions){
            createAnimal(position);
        }
    }

    private void createAnimal(Vector2d position){
        Animal newAnimal = new Animal(map, position, boundary);
        animalsList.add(newAnimal);
        map.place(newAnimal);
        newAnimal.positionChanged(newAnimal.position, newAnimal.position);
    }

    @Override
    public void run() {
        int movesLength = moves.length;
        int animalListLength = animalsList.size();

        for (int i = 0; i < movesLength; i++){
            animalsList.get(i % animalListLength).move(moves[i]);
        }
    }
}
