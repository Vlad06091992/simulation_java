package simulation.entities;

import simulation.data.Helpers;

public class Animal extends Entity {
    protected final Helpers helpers = new Helpers();
    protected int minimalValue = Integer.MAX_VALUE;;
    public Animal(String logo) {
        super(logo);
    }

    public void run(){

    }

}
