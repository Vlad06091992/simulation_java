package simulation;

import simulation.entities.Entity;
import simulation.entities.herbivores.*;
import simulation.entities.predators.*;

import java.util.*;

public class Field {
    private final List<Point> points = new ArrayList<>();

    int x;
    int y;



    public Field(int x, int y) {
        this.x = x;
        this.y = y;

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                points.add(new Point(j, i));
            }
        }

    }




    public void showMap(Map<Point, Entity> entities) {

        String field = "";
        for (int i = 0; i < points.size(); i++) {

            String end = (i + 1) % x == 0 ? "\n" : " ";


            Point point = points.get(i);
            if (entities.containsKey(point)) {
                field += entities.get(point).getLogo() + end;
            } else {
                field += points.get(i).toString() + end;
            }


        }
        System.out.println(field);
    }
}
