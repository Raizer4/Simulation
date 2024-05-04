package by.gr.project.сreature;

import by.gr.project.Coordinates;
import by.gr.project.Map;
import by.gr.project.entity.Entity;

public abstract class Creature extends Entity {

    int hp;

    public abstract Coordinates makeMove(Coordinates coordinates, Map size);

}
