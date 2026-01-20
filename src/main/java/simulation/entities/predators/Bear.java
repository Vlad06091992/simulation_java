package simulation.entities.predators;

import simulation.entities.base.Predator;

public class Bear extends Predator {
    public Bear(int health, int damage) {
        super("\uD83D\uDC15", health, damage);
    }
}