package simulation.entities;

import simulation.data.Point;
import simulation.data.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AliveEntity extends Entity {
    protected Utils utils = new Utils();
    protected int health;
    protected int damage;

    public AliveEntity(String logo, int health, int damage) {
        super(logo);
        this.health = health;
        this.damage = damage;
    }

    public void beEaten(int damage) {
        this.health -= damage;
    }

    public void run() {

    }

    public int getHealth() {
        return health;
    }

    protected void move(Point targetPoint) {
        entitiesMap.remove(getPoint());
        super.setPoint(targetPoint);
        entitiesMap.put(getPoint(), this);
    }

    public Optional<Point> findNearestEntity(Class<? extends Entity> targetType) {
        int pathLength = utils.getMaxInt();


        Map<Integer, Entity> grassPoints = new HashMap();
        Map<Point, Entity> entitiesMap = super.getEntitiesMap();

        for (Entity entity : entitiesMap.values()) {
            if (targetType.isInstance(entity)) {
                int value = utils.findPathLength(super.getPoint(), entity.getPoint());
                grassPoints.put(value, entity);
                if (value < pathLength) {
                    pathLength = value;
                }
            }

        }
        Entity entity = grassPoints.get(pathLength);

        if(entity == null){return Optional.empty();}

        return Optional.ofNullable(entity.getPoint());
    }

}
