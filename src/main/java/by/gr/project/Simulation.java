package by.gr.project;

import by.gr.project.actions.Actions;
import by.gr.project.actions.InitActions;
import by.gr.project.actions.TurnActions;

public class Simulation {

    public Map map = new Map(10);
    public int counter = 0;
    public Render render = new Render();
    public Actions initActions = new InitActions();
    public Actions turnActions = new TurnActions();
    public boolean isPause = false;


    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }

    public void startSimulation(){
        initActions.perform(map);

        while(!isPause && counter < 2){
            render.renderMap(map);
            turnActions.moveAnimals(map);
            counter++;
            System.out.println();
        }

    }

    public void pausesSimulation(){}


    void nextTurn(){}



}
