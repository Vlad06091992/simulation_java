package simulation.data;

import simulation.entities.Entity;

import java.util.*;

public class Field {
    private final List<Point> points = new ArrayList<>();

    int x;
    int y;

    public static class Size {
        private final int width;
        private final int height;

        public Size(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() { return width; }
        public int getHeight() { return height; }
    }
    public  Size getSize() {
        return new Size(x,y);
    }

    public Field(int x, int y) {
        this.x = x;
        this.y = y;

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                points.add(new Point(j, i));
            }
        }

    }

    private void cleanConsoleOutput() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
//            System.out.println(E);
        }
    }



    public void showMap(Map<Point, Entity> entities) {
        cleanConsoleOutput();

        String field = "";
        for (int i = 0; i < points.size(); i++) {

            String end = (i + 1) % x == 0 ? "\n" : "  ";


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
