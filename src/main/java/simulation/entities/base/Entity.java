package simulation.entities.base;



import simulation.map_elements.Field;
import simulation.map_elements.Point;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {
    protected Map<Point, Entity> entitiesMap = new HashMap<>();
    private Point point;
    private String logo;
    private Field field;


    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }


    public Entity(String logo) {
        this.logo = logo;
    }

    public void setEntitiesMap(Map<Point, Entity> entitiesMap) {
        this.entitiesMap = entitiesMap;
    }

    public Map<Point, Entity> getEntitiesMap() {
        return entitiesMap;
    }


    public String getLogo() {
        return " " + logo;
    }


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
