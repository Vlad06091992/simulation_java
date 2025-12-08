package simulation.entities.herbivores;

import simulation.data.Point;
import simulation.entities.AliveEntity;
import simulation.entities.predators.Predator;
import simulation.entities.statics.Grass;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class Herbivore extends AliveEntity {
    public Herbivore(String logo, int health, int damage) {
        super(logo, health, damage);
    }

    public void eat(Point grassPoint) {
        Grass grass = (Grass) super.getEntitiesMap().get(grassPoint);
        grass.beEaten(damage);
    }

    @Override
    public void run() {

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

                super.move(randomPoint.get());
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
        super.move(targetPoint);
    }


}
