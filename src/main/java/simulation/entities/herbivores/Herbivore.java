package simulation.entities.herbivores;

import simulation.data.Point;
import simulation.entities.AliveEntity;
import simulation.entities.Entity;
import simulation.entities.predators.Predator;
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

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(entity.getPoint());
    }

    public Optional<Point> findNearestPredator() {

        int pathLength = utils.getMaxInt();


        Map<Integer, Entity> predatorPoints = new HashMap();
        Map<Point, Entity> entitiesMap = super.getEntitiesMap();

        for (Entity entity : entitiesMap.values()) {
            if (entity instanceof Predator) {
                int value = utils.findPathLength(super.getPoint(), entity.getPoint());
                predatorPoints.put(value, entity);
                if (value < pathLength) {
                    pathLength = value;
                }
            }

        }
        Entity entity = predatorPoints.get(pathLength);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(entity.getPoint());
    }

    public void eat(Point grassPoint) {

        int damage = 1000;

        Grass grass = (Grass) super.getEntitiesMap().get(grassPoint);
        grass.beEaten(damage);
    }

    @Override
    public void run() {

        Point point = super.getPoint();
        Optional<Point> nearestGrass = findNearestGrass();
        Optional<Point> nearestPredator = findNearestPredator();
        Set<Point> availablePoints = utils.getAvailablePoints(point, getField(), getEntitiesMap());

        Optional<Point> randomPoint = availablePoints.stream()
                .skip(new Random().nextInt(availablePoints.size()))
                .findFirst();



        if (nearestGrass.isEmpty() || randomPoint.isEmpty()) {
            return;
        }
        int pathLength = utils.findPathLength(point, nearestGrass.get());

        if (pathLength == 0) {
            eat(nearestGrass.get());
            return;
        }


        if (nearestPredator.isPresent()) {
            int lengthToPredator = utils.findPathLength(point, nearestPredator.get());

            if (lengthToPredator < 2) {

                entitiesMap.remove(getPoint());
                super.setPoint(randomPoint.get());
                entitiesMap.put(getPoint(), this);
            }
        }




        Point nextPoint = utils.generateNextStepCoordinates(point, nearestGrass.get());
        Point targetPoint = availablePoints.contains(nextPoint) ? nextPoint : randomPoint.get();
        entitiesMap.remove(getPoint());
        super.setPoint(targetPoint);
        entitiesMap.put(getPoint(), this);
    }


}
