package dev.esz.aoc.day10;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.function.Supplier;

public interface Day10 {
    double EPS = 0.000001;

    static int part1(List<String> lines) {
        char[][] map = parseLines(lines);
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '#') {
                    Point point = new Point(i, j);
                    var partitionedPoints = partitionPoints(map, point);
                    int count = getUniqTangents(partitionedPoints, point).values().stream().mapToInt(List::size).sum();
                    if (max < count) {
                        max = count;
                    }
                }
            }
        }
        return max;
    }

    static int part2(List<String> lines) {
        char[][] map = parseLines(lines);
        Point maxPoint = new Point(0, 0);
        Map<Integer, List<Point>> partitionsByMaxPoint = new HashMap<>();

        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '#') {
                    Point point = new Point(i, j);
                    var partitionedPoints = partitionPoints(map, point);
                    int count = getUniqTangents(partitionedPoints, point).values().stream().mapToInt(List::size).sum();
                    if (max < count) {
                        max = count;
                        maxPoint = point;
                        partitionsByMaxPoint = partitionedPoints;
                    }
                }
            }
        }

        var degreesByPoints = groupByDegrees(partitionsByMaxPoint, maxPoint);

        int step = 1;
        var iterator = degreesByPoints.entrySet().iterator();
        Set<Integer> markForRemoval = new HashSet<>();
        while (!degreesByPoints.isEmpty()) {
            if (!iterator.hasNext()) {
                for (Integer key : markForRemoval) {
                    degreesByPoints.remove(key);
                }
                markForRemoval.clear();
                iterator = degreesByPoints.entrySet().iterator();
            }
            var entry = iterator.next();
            Queue<Point> points = entry.getValue();
            if (!points.isEmpty()) {
                Point point = points.poll();
                if (step == 200) {
                    return point.getY() * 100 + point.getX();
                }
                step++;
            } else {
                markForRemoval.add(entry.getKey());
            }
        }
        throw new IllegalStateException();
    }

    private static char[][] parseLines(List<String> lines) {
        char[][] map = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }
        return map;
    }

    private static Map<Integer, List<Point>> partitionPoints(char[][] map, Point current) {
        Map<Integer, List<Point>> classify = new HashMap<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (current.getX() == i && current.getY() == j) {
                    continue;
                }
                int x = i;
                int y = j;
                if (map[i][j] == '#') {
                    Supplier<Integer> computeCategory = () -> {
                        if (current.getX() > x && current.getY() <= y) {
                            return 1;
                        }
                        if (current.getX() > x && current.getY() > y) {
                            return 2;
                        }
                        if (current.getX() <= x && current.getY() > y) {
                            return 3;
                        }
                        if (current.getX() <= x && current.getY() <= y) {
                            return 4;
                        }
                        throw new IllegalStateException();
                    };
                    classify.merge(computeCategory.get(), new ArrayList<>(List.of(new Point(i, j))), (value, defaultValue) -> {
                        value.addAll(defaultValue);
                        return value;
                    });
                }
            }
        }
        return classify;
    }

    private static Map<Integer, List<Double>> getUniqTangents(Map<Integer, List<Point>> map, Point point) {
        Map<Integer, List<Double>> uniqTangents = new HashMap<>();
        for (var entry : map.entrySet()) {
            List<Double> tangents = new ArrayList<>();
            uniqTangents.put(entry.getKey(), tangents);
            for (Point otherPoint : entry.getValue()) {
                boolean contains = false;
                double tangent = computeTangent(point, otherPoint);
                for (Double otherTangent : tangents) {
                    if (Math.abs(otherTangent - tangent) <= EPS) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    tangents.add(tangent);
                }
            }
        }
        return uniqTangents;
    }

    private static Map<Integer, Queue<Point>> groupByDegrees(Map<Integer, List<Point>> classifiedPoints, Point point) {
        Map<Integer, Queue<Point>> degreeToPoint = new TreeMap<>();

        for (var entry : classifiedPoints.entrySet()) {
            for (Point otherPoint : entry.getValue()) {
                int degree = (int) (100 * (partToDegree(entry.getKey()) - Math.toDegrees(Math.atan(computeTangent(point, otherPoint)))));
                if (entry.getKey() == 2) {
                    degree = (int) (100 * (partToDegree(entry.getKey()) - 90 + Math.toDegrees(Math.atan(computeTangent(point, otherPoint)))));
                }
                Queue<Point> queue = new PriorityQueue<>(Comparator.comparingDouble(point::distanceSquared));
                queue.offer(otherPoint);
                degreeToPoint.merge(degree, queue, (value, defaultValue) -> {
                    value.addAll(defaultValue);
                    return value;
                });
            }
        }
        return degreeToPoint;
    }

    private static double computeTangent(Point p1, Point p2) {
        double a = Math.abs(p1.getX() - p2.getX());
        double b = Math.abs(p1.getY() - p2.getY());

        if (Math.abs(b - 0) <= EPS) {
            return Double.MAX_VALUE;
        }
        return a / b;
    }

    private static int partToDegree(int part) {
        switch (part) {
            case 1:
                return 90;
            case 2:
                return 360;
            case 3:
                return 270;
            case 4:
                return 180;
        }
        throw new IllegalStateException();
    }
}

@RequiredArgsConstructor
@Getter
@ToString
class Point {
    private final int x;
    private final int y;

    public double distanceSquared(Point other) {
        return (x - other.getX()) * (x - other.getX()) + (y - other.getY()) * (y - other.getY());
    }
}
