package agh.ics.oop;

import java.util.ArrayList;


public class SimulationEngine implements IEngine, Runnable{
    private MoveDirection[] moves;
    private final IWorldMap map;
    private final ArrayList<Animal> animalsList = new ArrayList<>();
    private final MapBoundary boundary;
    private final int moveDelay;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions, MapBoundary boundary,
                            int moveDelay){
        this.moves = moves;
        this.map = map;
        this.boundary = boundary;
        this.moveDelay = moveDelay;

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
    public void run(){
        int movesLength = moves.length;
        int animalListLength = animalsList.size();

        for (int i = 0; i < movesLength; i++){
            animalsList.get(i % animalListLength).move(moves[i]);
            try {
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException err){
                System.out.println(err);
            }

        }
    }

    public ArrayList<Animal> getAnimalsList(){
        return this.animalsList;
    }

    public void setMoves(MoveDirection[] newMoves){
        this.moves = newMoves;
    }
}
