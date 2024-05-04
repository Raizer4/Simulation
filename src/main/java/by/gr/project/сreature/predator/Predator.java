package by.gr.project.сreature.predator;

import by.gr.project.Coordinates;
import by.gr.project.Map;
import by.gr.project.entity.Entity;
import by.gr.project.сreature.Creature;
import by.gr.project.сreature.herbivore.Herbivore;

import java.util.*;

public class Predator extends Creature {


    @Override
    public Coordinates makeMove(Coordinates coordinates, Map map) {

        Coordinates nearestHerbivore = findNearestHerbivore(coordinates,map);

        List<Coordinates> neighbours = getNeighbours(coordinates,map.getSize());

        Coordinates herbivoreIsAvailable = checkIsHerbivoreIsAvailable(nearestHerbivore, neighbours);

        if (herbivoreIsAvailable != null){
            HashMap<Coordinates, Entity> table = map.getMap();
            Creature predator = (Creature) table.get(coordinates);
            table.remove(coordinates);
            table.put(herbivoreIsAvailable,predator);
            System.out.println("Животное - "+predator.toString()+" сьел травоядного " + coordinates + " " + nearestHerbivore);
            return herbivoreIsAvailable;
        }

        List<Coordinates> availableMoves = getAvailableMoves(neighbours,map.getMap());

        Coordinates bestMove = getBestMove(nearestHerbivore,availableMoves);

        HashMap<Coordinates, Entity> table = map.getMap();
        Creature predator = (Creature) table.get(coordinates);
        table.remove(coordinates);
        table.put(bestMove,predator);
        System.out.println("Животное - "+predator.toString()+" переместился к травоядному " + coordinates + " " + bestMove);
        return bestMove;
    }

    private Coordinates findNearestHerbivore(Coordinates coordinates,Map map)
    {

        Queue<Coordinates> queue = new LinkedList<>();
        queue.add(coordinates);
        HashMap<Coordinates, Entity> table = map.getMap();

        List<Coordinates> visited = new ArrayList<>();

        while(!queue.isEmpty()){
            Coordinates current = queue.poll();
            if (!visited.contains(current)){
                visited.add(current);
                List<Coordinates> neighbours = getNeighbours(current,map.getSize());

                for (Coordinates neighbour : neighbours){

                    Entity entity = table.get(neighbour);

                    if (entity instanceof Herbivore){
                        return neighbour;
                    }
                    queue.add(neighbour);
                }

            }

        }
        return null;
    }

    private List<Coordinates> getNeighbours(Coordinates temp, int size) {

        List<Coordinates> list = new ArrayList<>();

        int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};

        int x = temp.getX();
        int y = temp.getY();

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isInsideMap(newX, newY, size)) {
                list.add(new Coordinates(newX, newY));
            }
        }

        return list;
    }

    private boolean isInsideMap(int x, int y,int size) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private List<Coordinates> getAvailableMoves(List<Coordinates> neighbours,HashMap<Coordinates,Entity> table){

        List<Coordinates> list = new ArrayList<>();

        for (Coordinates temp : neighbours){
            Entity entity = table.get(temp);
            if (entity == null){
                list.add(temp);
            }
        }

        return list;
    }

    private Coordinates getBestMove(Coordinates nearestHerbivore ,List<Coordinates> availableMoves){
        Coordinates result = null;
        int minPath = Integer.MAX_VALUE;

        HashMap<Coordinates,Integer> map = new HashMap<>();

        for (Coordinates move : availableMoves){

            int x1 = move.getX();
            int y1 = move.getY();

            int x2 = nearestHerbivore.getX();
            int y2 = nearestHerbivore.getY();

            int path = Math.abs(x2 - x1) + Math.abs(y2 - y1);

            map.put(move,path);
        }

        for (Coordinates key : map.keySet()){
            Integer path = map.get(key);
            if (minPath > path){
                minPath = path;
                result = key;
            }
        }

        return result;
    }

    private Coordinates checkIsHerbivoreIsAvailable(Coordinates nearestHerbivore ,List<Coordinates> neighbors){
        for (Coordinates temp : neighbors){
            if ( temp.getX() == nearestHerbivore.getX() && temp.getY() == nearestHerbivore.getY() ){
                return temp;
            }
        }
        return null;
    }

    void attack(){

    }

}
