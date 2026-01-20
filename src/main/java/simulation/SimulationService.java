package simulation;

import simulation.entities.base.*;
import simulation.entities.static_environment.Grass;
import simulation.map_elements.Field;
import simulation.map_elements.Point;

import java.util.ArrayList;
import java.util.Map;

public class SimulationService {
    private int stepCount = 0;
    int predatorsCount = 0;
    int grassCount = 0;
    int herbivoresCount = 0;
    ArrayList<Entity> entities;
    private Field field;
    private final Map<Point, Entity> entitiesMap;

    public SimulationService(ArrayList<Entity> entities, Map<Point, Entity> entitiesMap, Field field) {
        this.entities = entities;
        this.entitiesMap = entitiesMap;
        this.field = field;
    }

    public boolean isGameInProgress() {
        if (predatorsCount == 0 || herbivoresCount == 0 || grassCount == 0) {
            System.out.println("игра закончена");
            return false;
        }
        return true;
    }

    public void nextTurn() throws InterruptedException {
        stepCount++;
        predatorsCount = 0;
        grassCount = 0;
        herbivoresCount = 0;

        removeDeadEntities();
        movingEntities();
        logGame();
    }

    private void removeDeadEntities() {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity instanceof LivingEntity) {
                if (((LivingEntity) entity).getHealth() <= 0) {
                    entities.remove(entity);
                    entitiesMap.remove(entity.getPoint());
                }
            }
        }
    }


    private void movingEntities() {
        //сущности меняют свои позиции
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            if (entity instanceof Predator) {
                predatorsCount++;
            }

            if (entity instanceof Herbivore) {
                herbivoresCount++;
            }

            if (entity instanceof Grass) {
                grassCount++;
            }

            if (entity instanceof Creature) {
                ((Creature) entity).makeMove();
            }
        }
    }

    private void logGame() {
        System.out.println("кол-во шагов: " + stepCount);
        System.out.println("кол-во хищников: " + predatorsCount);
        System.out.println("кол-во травоядных: " + herbivoresCount);
        System.out.println("кол-во травы: " + grassCount);
        field.showMap(entitiesMap);
    }
}
