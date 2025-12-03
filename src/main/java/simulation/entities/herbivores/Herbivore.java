package simulation.entities.herbivores;

import simulation.data.Point;
import simulation.entities.AliveEntity;
import simulation.entities.Entity;
import simulation.entities.statics.Grass;

import java.util.*;

public class Herbivore extends AliveEntity {


    public Herbivore(String logo) {
        super(logo, 300);
    }

    public Optional<Point> findNearestGrass() {

      int pathLength = utils.getMaxInt();


        Map<Integer, Entity> grassPoints = new HashMap();
        Map<Point, Entity> entitiesMap = super.getEntitiesMap();

        for (Entity entity : entitiesMap.values()) {
            if (entity instanceof Grass) {
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

    public void eat(Point grassPoint) {

        int damage = 10;

        Grass grass = (Grass) super.getEntitiesMap().get(grassPoint);
        grass.beEaten(damage);
    }

    @Override
    public void run() {

        Point point = super.getPoint();
        Optional<Point> target = findNearestGrass();
        Set<Point> availablePoints = utils.getAvailablePoints(point,getField(),getEntitiesMap());

        if (target.isEmpty()) {
            return;
        }

        int length = utils.findPathLength(point,target.get());

        if (length == 0) {
            eat(target.get());
            return;
        }

        Point nextPoint = utils.generateNextStepCoordinates(point, target.get());

        Optional<Point> randomPoint = availablePoints.stream()
                .skip(new Random().nextInt(availablePoints.size()))
                .findFirst();

        if (randomPoint.isEmpty()) {
            return;
        }

        Point targetPoint = availablePoints.contains(nextPoint) ? nextPoint : randomPoint.get();

        int pathLength = utils.findPathLength(point, target.get());
        if (pathLength == 0) {
            return;
        } else {
            super.setPoint(targetPoint);
        }
    }


}
