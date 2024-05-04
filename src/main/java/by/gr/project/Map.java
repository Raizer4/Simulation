package by.gr.project;

import by.gr.project.entity.Entity;

import java.util.HashMap;

public class Map {

    private int size;

    public HashMap<Coordinates, Entity> map = new HashMap<>();

    public HashMap<Coordinates, Entity> getMap(){
        return map;
    }

    public void setMap(HashMap<Coordinates,Entity> map){
        this.map = map;
    }


    public void add(Coordinates coordinates,Entity entity){

        if (coordinates.getX() > size || coordinates.getY() > size){
            throw new RuntimeException();
        }



        map.put(coordinates,entity);
    }

    public Map(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
