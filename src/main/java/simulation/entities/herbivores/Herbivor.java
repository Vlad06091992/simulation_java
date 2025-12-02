package simulation.entities.herbivores;

import simulation.data.Point;
import simulation.entities.Animal;
import simulation.entities.Entity;
import simulation.entities.statics.Grass;

import java.util.*;

public class Herbivor extends Animal {

    public Herbivor(String logo) {
        super(logo);
    }

    public Point findNearestGrass() {
        Map<Integer, Entity> grassPoints = new HashMap();
        Map<Point, Entity> entitiesMap = super.getEntitiesMap();

        for (Entity entity : entitiesMap.values()) {
            if (entity instanceof Grass) {
                int value = helpers.findPathLength(super.getPoint(), entity.getPoint());
                grassPoints.put(value, entity);
                if (value < minimalValue) {
                    minimalValue = value;
                }
            }

        }
        return grassPoints.get(minimalValue).getPoint();
    }

    @Override
    public void run() {

        Point point = super.getPoint();
        Point target = findNearestGrass();
        Set<Point> availablePoints = helpers.getAvailablePoints(point,getField(),getEntitiesMap());


        Point nextPoint = helpers.generateNextStepCoordinates(point, target);

        System.out.println("availablePoints:" + availablePoints.toString());

        Optional<Point> randomPoint = availablePoints.stream()
                .skip(new Random().nextInt(availablePoints.size()))
                .findFirst();

        if (randomPoint.isEmpty()) {
            return;
        }

        Point targetPoint = availablePoints.contains(nextPoint) ? nextPoint : randomPoint.get();

        int pathLength = helpers.findPathLength(point, target);
        if (pathLength == 0) {
            return;
        } else {
            super.setPoint(targetPoint);
        }
    }


}
