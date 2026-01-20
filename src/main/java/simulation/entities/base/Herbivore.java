package simulation.entities.base;

import simulation.map_elements.Point;
import simulation.entities.static_environment.Grass;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class Herbivore extends Creature {
    public Herbivore(String logo, int health, int damage) {
        super(logo, health, damage);
    }

    public void eat(Point grassPoint) {
        Grass grass = (Grass) super.getEntitiesMap().get(grassPoint);
        grass.beEaten(damage);
        super.eat();
    }

    @Override
    public void makeMove() {

        Point point = super.getPoint();
        Optional<Point> nearestGrass = findNearestEntity(Grass.class);
        Optional<Point> nearestPredator = findNearestEntity(Predator.class);
        Set<Point> availablePoints = utils.getAvailablePoints(point, getField(), getEntitiesMap());

        int n = new Random().nextInt(availablePoints.size());
        Optional<Point> randomPoint = availablePoints.stream()
                .skip(n)
                .findFirst();


        if (nearestGrass.isEmpty() || randomPoint.isEmpty()) {
            return;
        }

        if (nearestPredator.isPresent()) {
            int lengthToPredator = utils.findPathLength(point, nearestPredator.get());

            if (lengthToPredator < 2) {

                super.moving(randomPoint.get());
                return;
            }
        }
        int pathLength = utils.findPathLength(point, nearestGrass.get());

        if (pathLength == 0) {
            eat(nearestGrass.get());
            return;
        }


        Point nextPoint = utils.generateNextStep(point, nearestGrass.get());
        Point targetPoint = availablePoints.contains(nextPoint) ? nextPoint : randomPoint.get();
        super.moving(targetPoint);
    }


}
