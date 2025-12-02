package simulation;

import simulation.data.Field;
import simulation.data.Helpers;
import simulation.data.Point;
import simulation.entities.Animal;
import simulation.entities.Entity;
import simulation.entities.herbivores.entities.Sheep;
import simulation.entities.predators.entities.Tiger;
import simulation.entities.statics.Grass;

import java.util.*;

public class Simulation {

    private static Field field;
    private static final Map<Point, Entity> entitiesMap = new HashMap<>();
    private static final ArrayList<Entity> entities = new ArrayList<>(Arrays.asList(
//            new Tiger(),
//            new Wolf(),
//            new Fox(),
//            new Crocodile(),
//            new Bear(),
//            new Rabbit(),
//            new Cow(),
//            new Deer(),
            new Sheep(),
//            new Grass(),
//            new Grass(),
            new Grass()
//            new Grass()
//            new Goat()
    ));

    private static final Helpers helpers = new Helpers();


    public void run(int x,int y,int sleep) throws InterruptedException {
        init(x,y);
//        while (true) {
//            Thread.sleep(sleep);
            action();
//        }

    }

    private static void action() throws InterruptedException {

        // даем сущности доступ к полю с координатами других существ
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.setEntitiesMap(entitiesMap);
            entity.setField(field);
        }


//сущности меняют свои позиции
        for (Entity entity : entities) {
            if (entity instanceof Animal) {
                ((Animal) entity).run();
            }
        }

        entitiesMap.clear();

//        //наносим их на карту
//        for (int i = 0; i < entities.size(); i++) {
//            Entity entity = entities.get(i);
//            entitiesMap.put(entity.getPoint(), entity);
//        }



        field.showMap(entitiesMap);

    }


    private static void init(int x, int y)  {

        field = new Field(x,y);

        // сущностей создали, надо сгенерировать им позиции
        for (int i = 0; i < entities.size(); i++) {
            Point currentPoint = new Point(helpers.getRandomInt(x, "x"), helpers.getRandomInt(y, "y"));
            Entity entity = entities.get(i);
            entity.setPoint(currentPoint);
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
        }

        field.showMap(entitiesMap);
    }


}
