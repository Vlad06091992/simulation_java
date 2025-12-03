package simulation;

import simulation.data.Field;
import simulation.data.Utils;
import simulation.data.Point;
import simulation.entities.AliveEntity;
import simulation.entities.Entity;
import simulation.entities.herbivores.entities.*;
import simulation.entities.statics.Grass;

import java.util.*;
import java.util.stream.Collectors;

public class Simulation {

    private static Field field;
    private static final Map<Point, Entity> entitiesMap = new HashMap<>();
    private static ArrayList<Entity> entities = new ArrayList<>(Arrays.asList(
//            new Tiger(),
//            new Wolf(),
//            new Fox(),
//            new Crocodile(),
//            new Bear(),
            new Rabbit(),
            new Cow(),
//            new Deer(),
//            new Sheep(),
//            new Grass(),
            new Grass(),
            new Grass(),
            new Grass()
//            new Goat()
    ));

    private static final Utils helpers = new Utils();


    public void run(int x,int y,int sleep) throws InterruptedException {
        init(x, y);
        while (true) {
            Thread.sleep(sleep);
            action();
//        }

        }
    }

    public void  action() throws InterruptedException {

        entities = entities.stream()
        .filter(entity -> {
                    if (!(entity instanceof Grass)) {
                        return true;  // явный возврат true
                    } else {

//                        System.out.println(((Animal) entity).getHealth());
                        // Явный возврат false или true в зависимости от условия
                        return ((AliveEntity) entity).getHealth() > 0;
//                        return false;
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));

//        System.out.println(entities.get(0).toString());
//        System.out.println(entities.get(1).toString());

        // даем сущности доступ к полю с координатами других существ
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.setEntitiesMap(entitiesMap);
            entity.setField(field);
        }


//сущности меняют свои позиции
        for (Entity entity : entities) {
            if (entity instanceof AliveEntity) {
                ((AliveEntity) entity).run();
            }
        }

        entitiesMap.clear();

        //наносим их на карту
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entitiesMap.put(entity.getPoint(), entity);
        }



        field.showMap(entitiesMap);

    }


    public  void init(int x, int y)  {

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
