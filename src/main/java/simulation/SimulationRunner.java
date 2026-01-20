package simulation;

import simulation.map_elements.Field;
import simulation.map_elements.Point;
import simulation.helpers.Utils;
import simulation.entities.base.Entity;
import simulation.entities.herbivores.*;
import simulation.entities.predators.*;
import simulation.entities.static_environment.Grass;
import simulation.entities.static_environment.Rock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimulationRunner {
    private SimulationService simulationService;
    private final Utils utils = new Utils();
    private final Map<Point, Entity> entitiesMap = new HashMap<>();
    private final ArrayList<Entity> entities = new ArrayList<>(Arrays.asList(

            //камни
            new Rock(),
            new Rock(),
            new Rock(),
            new Rock(),
            new Rock(),
            new Rock(),

            //хищники
            new Tiger(350, 20),
            new Wolf(320, 17),
            new Fox(300, 7),
            new Crocodile(500, 28),
            new Bear(590, 37),

            //травоядные
            new Cow(600, 80),
            new Sheep(200, 60),
            new Rabbit(100, 40),
            new Deer(800, 140),
            new Goat(400, 60),
            //трава
            new Grass(120),
            new Grass(90),
            new Grass(60),
            new Grass(30)
    ));


    public void startSimulation(int x, int y, int sleep) throws InterruptedException {
        init(x, y);
        boolean gameInProgress = true;
        while (gameInProgress) {
            Thread.sleep(sleep);
            simulationService.nextTurn();
            gameInProgress = simulationService.isGameInProgress();
        }
    }


    public void init(int x, int y) {
        Field field = new Field(x, y);
        simulationService = new SimulationService(entities, entitiesMap, field);

        // сущностей создали, надо сгенерировать им позиции
        for (int i = 0; i < entities.size(); i++) {
            Point point = utils.getUniqRandomPoint(x);
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
