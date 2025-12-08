package simulation.entities.predators;

import simulation.data.Point;
import simulation.entities.AliveEntity;
import simulation.entities.Entity;
import simulation.entities.herbivores.Herbivore;

import java.util.*;

public class Predator extends AliveEntity {

    public Predator(String logo, int health, int damage) {
        super(logo, health,damage);
    }

    public void eat(Point herbivorePoint) {
        Herbivore herbivore = (Herbivore) super.getEntitiesMap().get(herbivorePoint);
        herbivore.beEaten(damage);
    }


    @Override
    public void run() {
        this.health = this.health - 10;

        Point point = super.getPoint();
        Optional<Point> nearestHerbivore = findNearestEntity(Herbivore.class);

        if(nearestHerbivore.isEmpty()){
            return;
        }

        int length = utils.findPathLength(point,nearestHerbivore.get());

        if (length == 0) {
            eat(nearestHerbivore.get());
            return;
        }

        Set<Point> availablePoints = utils.getAvailablePoints(point, getField(), getEntitiesMap());
        Optional<Point> randomPoint = availablePoints.stream()
                .skip(new Random().nextInt(availablePoints.size()))
                .findFirst();



        if (randomPoint.isEmpty()) {
            return;
        }

        Point nextPoint = utils.generateNextStep(point, nearestHerbivore.get());
        Point targetPoint = availablePoints.contains(nextPoint) ? nextPoint : randomPoint.get();
        super.move(targetPoint);
    }
}


