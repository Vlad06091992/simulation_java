package simulation.data;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    String show;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "---";
    }

    public Point(int x, int y, String show) {
        this.x = x;
        this.y = y;
        this.show = show;
    }

    public Point(int x, int y) {
        this(x, y, "x");
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
