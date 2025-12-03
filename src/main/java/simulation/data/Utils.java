package simulation.data;

import simulation.entities.Entity;

import java.util.*;

public class Utils {

    Set<Integer> xSet = new HashSet<>();
    Set<Integer> ySet = new HashSet<>();

    public Integer getRandomInt(Integer to, String type) {
        Set<Integer> targetSet = "x".equals(type) ? xSet : ySet;
        Random random = new Random();

        Integer result;
        do {
            result = random.nextInt(to - 1);
        } while (targetSet.contains(result)); // Генерируем, пока не найдем уникальное

        targetSet.add(result);
        return result + 1;
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

        if(moduleX <= 1 && moduleY <= 1) return 0;
        return Math.min(x, y);
    }

    private void findPath(int start, int end, List<Integer> coordinates) {

        if (start == end) {
            coordinates.add(start);
        }

        if (start == end - 1) {
            coordinates.add(end);
        }

        if (end == start - 1) {
            coordinates.add(start);
        }

        if (start < end) {
            for (int i = start + 1; i < end; i++) {
                coordinates.add(i);

            }
        } else if (start > end) {
            for (int i = start - 1; i > end; i--) {
                coordinates.add(i);
            }
        }
    }

    public List<Point> generateCoordinates(Point start, Point end) {
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        List<Point> pointList = new ArrayList<>();

        int xStart = start.getX();
        int yStart = start.getY();

        int xEnd = end.getX();
        int yEnd = end.getY();


        findPath(xStart, xEnd, xList);
        findPath(yStart, yEnd, yList);

        int stepsLength = Math.max(xList.size(), yList.size());
        int findPathLength = findPathLength(start, end);

        for (int i = 0; i < stepsLength; i++) {
            int x = i > xList.size() - 1 ? xList.get(xList.size() - 1) : xList.get(i);
            int y = i > yList.size() - 1 ? yList.get(yList.size() - 1) : yList.get(i);

            pointList.add(new Point(x, y));
        }

//        pointList.add(end);
//        pointList.add(0,start);

        return pointList;

    }

    public Point generateNextStepCoordinates(Point start, Point end) {
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        List<Point> pointList = new ArrayList<>();

        int xStart = start.getX();
        int yStart = start.getY();

        int xEnd = end.getX();
        int yEnd = end.getY();


        findPath(xStart, xEnd, xList);
        findPath(yStart, yEnd, yList);

        int stepsLength = Math.max(xList.size(), yList.size());
        int findPathLength = findPathLength(start, end);

        for (int i = 0; i < 1; i++) {
            int x = i > xList.size() - 1 ? xList.get(xList.size() - 1) : xList.get(i);
            int y = i > yList.size() - 1 ? yList.get(yList.size() - 1) : yList.get(i);

            pointList.add(new Point(x, y));
        }

//        pointList.add(end);
//        pointList.add(0,start);

        return pointList.get(0);

    }


    public Set<Point> getAvailablePoints(Point currPosition, Field field, Map<Point, Entity> entitiesMap) {
        System.out.println("currPosition: " + currPosition.toString());
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
            } else {
                System.out.println("hm");
            }

        }
        return pointList;
    }

    public int getMaxInt(){
      return Integer.MAX_VALUE;
    }
}
