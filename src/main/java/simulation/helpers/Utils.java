package simulation.helpers;

import simulation.entities.base.Entity;
import simulation.map_elements.Field;
import simulation.map_elements.Point;

import java.util.*;

public class Utils {

    Set<Point> createdPoints = new HashSet<>();

    public int getRandomInt(Integer to) {
        Random random = new Random();
        return random.nextInt(to) + 1;
    }

    public Point getUniqRandomPoint(Integer to) {
        Point newPoint = getRandomPoint(to);

        if (!createdPoints.contains(newPoint)) {
            createdPoints.add(newPoint);
            return newPoint;
        } else {
            return getUniqRandomPoint(to);
        }
    }

    public Point getRandomPoint(Integer to) {
        int x = getRandomInt(to);
        int y = getRandomInt(to);
        return new Point(x, y);
    }


    public int findPathLength(int start, int end) {

        int count = 0;

        if (start == end) {
            count++;
        }

        if (start == end - 1) {
            count++;
        }

        if (end == start - 1) {
            count++;
        }

        if (start < end) {
            for (int i = start + 1; i < end; i++) {
                count++;

            }
        } else if (start > end) {
            for (int i = start - 1; i > end; i--) {
                count++;
            }
        }

        return count;
    }

    public int findPathLength(Point start, Point end) {
        int x = findPathLength(start.getX(), end.getX());
        int y = findPathLength(start.getY(), end.getY());

        int moduleX = Math.abs(start.getX() - end.getX());
        int moduleY = Math.abs(start.getY() - end.getY());

        if (moduleX <= 1 && moduleY <= 1) return 0;
        return Math.min(x, y);
    }

    public Point generateNextStep(Point start, Point end) {
        int xStart = start.getX();
        int yStart = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();
        int x = findNextCoordinate(xStart,xEnd);
        int y = findNextCoordinate(yStart,yEnd);

        return new Point(x, y);
    }


    public Set<Point> getAvailablePoints(Point currPosition, Field field, Map<Point, Entity> entitiesMap) {
        Field.Size size = field.getSize();
        int width = size.getWidth();
        int height = size.getHeight();

        Set<Point> pointList = new HashSet<>();

        int currX = currPosition.getX();
        int currY = currPosition.getY();

        int bottomY = currY - 1;
        int leftX = currX - 1;

        int rightX = currX + 1;
        int topY = currY + 1;

        List<Point> pointMap = new ArrayList<>();
        pointMap.add(new Point(leftX, currY));
        pointMap.add(new Point(leftX, topY));
        pointMap.add(new Point(currX, topY));
        pointMap.add(new Point(rightX, topY));
        pointMap.add(new Point(rightX, currY));
        pointMap.add(new Point(rightX, bottomY));
        pointMap.add(new Point(currX, bottomY));
        pointMap.add(new Point(leftX, bottomY));


        for (Point p : pointMap) {
            int x = p.getX();
            int y = p.getY();

            boolean b = (x > 0 && y > 0) && (x <= width && y <= height) && (!entitiesMap.containsKey(p));
            if (b) {
                pointList.add(p);
            }
        }
        return pointList;
    }

    public int getMaxInt() {
        return Integer.MAX_VALUE;
    }

    private int findNextCoordinate(int start, int end) {

        if (start == end || end == start - 1) {
            return start;
        }

        if (start == end - 1) {
            return end;
        }

        if (start < end) {
            return start + 1;
        } else if (start > end) {
            return start - 1;
        }
        return start;
    }
}
