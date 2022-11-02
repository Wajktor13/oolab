package agh.ics.oop;

import java.util.ArrayList;


public class SimulationEngine implements IEngine{
    private final MoveDirection[] moves;
    private final IWorldMap map;
    private final ArrayList<Animal> animalsList = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions){
        this.moves = moves;
        this.map = map;

        for (Vector2d position : initialPositions){
            createAnimal(position);
        }
    }

    private void createAnimal(Vector2d position){
        if (map.canMoveTo(position)){
            Animal newAnimal = new Animal(map, position);
            animalsList.add(newAnimal);
            map.place(newAnimal);
        }
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
