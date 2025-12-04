package simulation;

import simulation.data.Field;
import simulation.data.Point;
import simulation.data.Utils;
import simulation.entities.AliveEntity;
import simulation.entities.Entity;
import simulation.entities.herbivores.Herbivore;
import simulation.entities.herbivores.entities.*;
import simulation.entities.predators.Predator;
import simulation.entities.predators.entities.*;
import simulation.entities.statics.Grass;
import simulation.entities.statics.Rock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Simulation {

    private int stepCount = 0;
    private boolean gameRun = true;
    private static Field field;
    private static final Map<Point, Entity> entitiesMap = new HashMap<>();
    private static ArrayList<Entity> entities = new ArrayList<>(Arrays.asList(

            //камни
            new Rock(),
            new Rock(),
            new Rock(),
            new Rock(),
            new Rock(),
            new Rock(),

            //хищники
            new Tiger(500,25),
            new Wolf(400,20),
            new Fox(400,10),
            new Crocodile(700,35),
            new Bear(750,45),
            //травоядные

            new Cow(600,40),
            new Sheep(200,30),
            new Rabbit(100,20),
            new Deer(800,70),
            new Goat(400,30),
            //трава
            new Grass(300),
            new Grass(200),
            new Grass(200),
            new Grass(100)
    ));

    private static final Utils helpers = new Utils();


    public void run(int x, int y, int sleep) throws InterruptedException {
        init(x, y);
        while (gameRun) {
            Thread.sleep(sleep);
            action();
        }
    }

    public void action() throws InterruptedException {
        stepCount++;
        int predatorsCount = 0;
        int grassCount = 0;
        int herbivoresCount = 0;


        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity instanceof AliveEntity) {
                if (((AliveEntity) entity).getHealth() <= 0) {
                    entities.remove(entity);
                    entitiesMap.remove(entity.getPoint());
                }
            }
        }

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

            if (entity instanceof AliveEntity) {
                ((AliveEntity) entity).run();
            }
        }


        System.out.println("кол-во шагов: " + stepCount);
        System.out.println("кол-во хищников: " + predatorsCount);
        System.out.println("кол-во травоядных: " + herbivoresCount);
        System.out.println("кол-во травы: " + grassCount);

        if (predatorsCount == 0 || herbivoresCount == 0 || grassCount == 0) {
            System.out.println("игра закончена");
            gameRun = false;
        }

        field.showMap(entitiesMap);

    }


    public void init(int x, int y) {

        field = new Field(x, y);

        // сущностей создали, надо сгенерировать им позиции
        for (int i = 0; i < entities.size(); i++) {
            Point point = helpers.getUniqRandomPoint(x);
            Entity entity = entities.get(i);
            entity.setPoint(point);
        }

        //наносим их на карту
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entitiesMap.put(entity.getPoint(), entity);
        }

        // даем сущности доступ к полю с координатами других существ
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.setEntitiesMap(entitiesMap);
            entity.setField(field);
        }

        field.showMap(entitiesMap);
    }


}
