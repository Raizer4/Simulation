package by.gr.project.сreature.herbivore;


import by.gr.project.Coordinates;
import by.gr.project.Map;
import by.gr.project.entity.Entity;
import by.gr.project.entity.subjects.Grass;
import by.gr.project.сreature.Creature;

import java.util.*;

public class Herbivore extends Creature {

    @Override
    public Coordinates makeMove(Coordinates coordinates, Map map) {

        Coordinates nearestGrass = findNearestGrass(coordinates,map);

        List<Coordinates> neighbours = getNeighbours(coordinates,map.getSize());

        Coordinates grassIsAvailable = checkIsGrassIsAvailable(nearestGrass, neighbours);

        if (grassIsAvailable != null){
            HashMap<Coordinates, Entity> table = map.getMap();
            Creature herbivore = (Creature) table.get(coordinates);
            table.remove(coordinates);
            table.put(grassIsAvailable,herbivore);
            System.out.println("Животное - "+herbivore.toString()+" сьел траву " + coordinates + " " + nearestGrass);
            return grassIsAvailable;
        }

        List<Coordinates> availableMoves = getAvailableMoves(neighbours,map.getMap());

        Coordinates bestMove = getBestMove(nearestGrass,availableMoves);

        HashMap<Coordinates, Entity> table = map.getMap();
        Creature herbivore = (Creature) table.get(coordinates);
        table.remove(coordinates);
        table.put(bestMove,herbivore);
        System.out.println("Животное - "+herbivore.toString()+" переместился к траве " + coordinates + " " + bestMove);
        return bestMove;
    }

    private Coordinates findNearestGrass(Coordinates coordinates,Map map)
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

                    if (entity instanceof Grass){
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

    private Coordinates getBestMove(Coordinates nearestGrass ,List<Coordinates> availableMoves){
        Coordinates result = null;
        int minPath = Integer.MAX_VALUE;

        HashMap<Coordinates,Integer> map = new HashMap<>();

        for (Coordinates move : availableMoves){

            int x1 = move.getX();
            int y1 = move.getY();

            int x2 = nearestGrass.getX();
            int y2 = nearestGrass.getY();

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

    private Coordinates checkIsGrassIsAvailable(Coordinates nearestGrass ,List<Coordinates> neighbors){
        for (Coordinates temp : neighbors){
            if ( temp.getX() == nearestGrass.getX() && temp.getY() == nearestGrass.getY() ){
                return temp;
            }
        }
        return null;
    }

    void eat(){

    }


}
