package simulation.entities;

import simulation.data.Utils;

public class AliveEntity extends Entity {
    protected Utils utils = new Utils();
    private int health;

    public AliveEntity(String logo, int health) {
        super(logo);
        this.health = health;
    }

    public void beEaten(int damage) {
        this.health -= damage;
    }

    public void run() {

    }

    public int getHealth() {
        return health;
    }

}
