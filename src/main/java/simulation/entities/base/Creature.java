package simulation.entities.base;

import simulation.map_elements.Point;
import simulation.helpers.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Creature extends LivingEntity {
    protected Utils utils = new Utils();
    protected int damage;

    public Creature(String logo, int health, int damage) {
        super(logo, health);
        this.damage = damage;
    }

    public void eat() {
        this.health += this.damage;
    }


    public abstract void makeMove();

    protected void moving(Point targetPoint) {
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

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(entity.getPoint());
    }

}
