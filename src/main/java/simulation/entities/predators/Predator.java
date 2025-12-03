package simulation.entities.predators;

import simulation.data.Point;
import simulation.entities.AliveEntity;
import simulation.entities.Entity;
import simulation.entities.herbivores.Herbivore;
import simulation.entities.statics.Grass;

import java.util.*;

public class Predator extends AliveEntity {

    public void eat(Point herbivorePoint) {

        int damage = 10;

        Herbivore herbivore = (Herbivore) super.getEntitiesMap().get(herbivorePoint);
        herbivore.beEaten(damage);
    }


    @Override
    public void run() {


        Point point = super.getPoint();
        Optional<Point> nearestHerbivore = findNearestHerbivore();

        if(nearestHerbivore.isEmpty()){
            return;
        }

        int length = utils.findPathLength(point,nearestHerbivore.get());
        Set<Point> availablePoints = utils.getAvailablePoints(point, getField(), getEntitiesMap());
        Optional<Point> randomPoint = availablePoints.stream()
                .skip(new Random().nextInt(availablePoints.size()))
                .findFirst();



        if (randomPoint.isEmpty()) {
            return;
        }

        if (length == 0) {
            eat(nearestHerbivore.get());
            return;
        }


        Point nextPoint = utils.generateNextStepCoordinates(point, nearestHerbivore.get());
        Point targetPoint = availablePoints.contains(nextPoint) ? nextPoint : randomPoint.get();
        super.setPoint(targetPoint);
    }

    public Predator(String logo) {
        super(logo, 100);
    }


    public Optional<Point> findNearestHerbivore() {

        int pathLength = utils.getMaxInt();


        Map<Integer, Entity> grassPoints = new HashMap();
        Map<Point, Entity> entitiesMap = super.getEntitiesMap();

        for (Entity entity : entitiesMap.values()) {
            if (entity instanceof Herbivore) {
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


