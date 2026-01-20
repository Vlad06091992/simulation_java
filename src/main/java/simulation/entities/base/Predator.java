package simulation.entities.base;

import simulation.map_elements.Point;

import java.util.*;

public class Predator extends Creature {

    public Predator(String logo, int health, int damage) {
        super(logo, health,damage);
    }

    public void eat(Point herbivorePoint) {
        Herbivore herbivore = (Herbivore) super.getEntitiesMap().get(herbivorePoint);
        herbivore.beEaten(damage);
        super.eat();
    }


    @Override
    public void makeMove() {
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
        super.moving(targetPoint);
    }
}


