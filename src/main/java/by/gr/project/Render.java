package by.gr.project;

import by.gr.project.entity.Entity;

import java.util.HashMap;

public class Render {

    public void renderMap(Map map) {

        HashMap<Coordinates, Entity> table = map.getMap();
        int size = map.getSize();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                if (table.get(new Coordinates(x, y)) == null)
                {
                    System.out.print("... ");
                }else{
                    System.out.print(table.get(new Coordinates(x, y))+" ");
                }


            }
            System.out.println();
        }

    }


}
