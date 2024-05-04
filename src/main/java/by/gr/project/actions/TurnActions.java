package by.gr.project.actions;

import by.gr.project.Coordinates;
import by.gr.project.Map;
import by.gr.project.entity.Entity;
import by.gr.project.сreature.Creature;
import by.gr.project.сreature.herbivore.Herbivore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TurnActions extends Actions{

    public void moveAnimals(Map map){

        int size = map.getSize();
        HashMap<Coordinates, Entity> table = map.getMap();
        List<Coordinates> disableMoves = new ArrayList<>();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Coordinates current = new Coordinates(x, y);
                if (disableMoves.contains(current)){
                    continue;
                }
                Entity entity = table.get(current);

                if (entity instanceof Creature)
                {

                   Creature creature = (Creature) entity;
                   Coordinates move = creature.makeMove(new Coordinates(x,y),map);
                   disableMoves.add(move);
                }
            }
        }
        disableMoves.clear();

    }

}
