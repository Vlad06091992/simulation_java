package simulation.entities.base;

public class LivingEntity extends Entity {
    protected int health;

    public LivingEntity(String logo, int health) {
        super(logo);
        this.health = health;
    }

    public void beEaten(int damage) {
        this.health -= damage;
    }

    public int getHealth() {
        return health;
    }
}
