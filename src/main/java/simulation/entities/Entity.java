package simulation.entities;



import simulation.Point;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {
    public void setEntitiesMap(Map<Point, Entity> entitiesMap) {
        this.entitiesMap = entitiesMap;
    }

    public Map<Point, Entity> getEntitiesMap() {
        return entitiesMap;
    }

    private Map<Point, Entity> entitiesMap = new HashMap<>();
    private Point point;
    private String logo;

    public Entity(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

}
