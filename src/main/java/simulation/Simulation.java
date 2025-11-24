package simulation;

import simulation.entities.Entity;
import simulation.entities.herbivores.*;
import simulation.entities.predators.*;

import java.util.*;

public class Simulation {

    private static final Helpers helpers = new Helpers();

    public static void main(String[] args) {
     init();
    }

    private static void init(){
        Field field = new Field(20, 20);
        Map<Point, Entity> entitiesMap = new HashMap<>();
        ArrayList<Entity> entities = new ArrayList<>(Arrays.asList(
                new Tiger(),
                new Wolf(),
                new Fox(),
                new Crocodile(),
                new Bear(),
                new Rabbit(),
                new Cow(),
                new Deer(),
                new Sheep(),
                new Goat()
        ));
        // сущностей создали, надо сгенерировать им позиции
        for (int i = 0; i < entities.size(); i++) {
            Point currentPoint = new Point(helpers.getRandomInt(20, "x"), helpers.getRandomInt(20, "y"));
            Entity entity = entities.get(i);
            entity.setPoint(currentPoint);
        }

        //наносим их на карту
        for(int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entitiesMap.put(entity.getPoint(), entity);
        }

        // даем сущности доступ к полю с координатами других существ
        for(int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.setEntitiesMap(entitiesMap);
        }

        field.showMap(entitiesMap);
        System.out.println("----------------------------------");
        System.out.println(entities.get(3).getEntitiesMap().toString());
    }


}
