package by.gr.project.actions;

import by.gr.project.Coordinates;
import by.gr.project.Map;
import by.gr.project.Render;
import by.gr.project.entity.Entity;
import by.gr.project.entity.subjects.Grass;
import by.gr.project.entity.subjects.Rock;
import by.gr.project.entity.subjects.Subject;
import by.gr.project.entity.subjects.Tree;
import by.gr.project.сreature.Creature;
import by.gr.project.сreature.herbivore.Antelope;
import by.gr.project.сreature.herbivore.Duck;
import by.gr.project.сreature.herbivore.Hedgehog;
import by.gr.project.сreature.predator.Bear;
import by.gr.project.сreature.predator.Tiger;
import by.gr.project.сreature.predator.Wolf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InitActions extends Actions{
    @Override
    public void perform(Map gameMap){
        initCreatures(gameMap);
        initSubjects(gameMap);
    }

    private static void initCreatures(Map gameMap){

        HashMap<Coordinates, Entity> map = gameMap.getMap();
        int size = gameMap.getSize();

        Random random = new Random();

        int totalCells = size * size;
        int randomCellsCount = (int) (totalCells * 0.1);


        for (int i = 0; i < randomCellsCount;i++) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            Coordinates coordinates = new Coordinates(x,y);

            if (map.containsKey(coordinates)){
                randomCellsCount++;
                continue;
            }


            map.put(coordinates, getRandomCreature());
        }

        gameMap.setMap(map);

    }
    private static Creature getRandomCreature(){
        Creature[] animals = {new Antelope(),new Duck(),new Bear(),new Hedgehog(),new Tiger(),new Wolf()};
        Random random = new Random();
        int animalsIndex = random.nextInt(6);
        return animals[animalsIndex];
    }

    private static void initSubjects(Map gameMap) {

        HashMap<Coordinates, Entity> map = gameMap.getMap();
        int size = gameMap.getSize();

        Random random = new Random();

        int totalCells = size * size;
        int randomCellsCount = (int) (totalCells * 0.2);


        for (int i = 0; i < randomCellsCount;i++) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            Coordinates coordinates = new Coordinates(x,y);

            if (map.containsKey(coordinates)){
                randomCellsCount++;
                continue;
            }


            map.put(coordinates,getRandomSubject());

        }
    }

    private static Subject getRandomSubject() {
        Subject[] subjects = {new Grass(),new Rock(),new Tree()};
        Random random = new Random();
        int subjectIndex = random.nextInt(3);
        return subjects[subjectIndex];
    }

}
