package simulation.data;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    String show;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCoordinates() {
        return x + "" + y;
    }

//    @Override
//    public String toString() {
//        return show;
//    }

    @Override
    public String toString() {

//        if (x == 3 && y == 1) {
//            return "\uD83D\uDC05";
//        }

//        return x + " " + y;
        return "---";
    }

    public Point(int x, int y, String show) {
        this.x = x;
        this.y = y;
        this.show = show;
    }

    public Point(int x, int y) {
        this(x, y, "x"); // вызов основного конструктора
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y && Objects.equals(show, point.show);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, show);
    }
}
