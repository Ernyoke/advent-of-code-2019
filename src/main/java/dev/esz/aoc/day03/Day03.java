package dev.esz.aoc.day03;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

import java.util.*;

public interface Day03 {
    static int part1(List<String> wire1, List<String> wire2) {
        Set<Position> wirePositions1 = convertToPositions(wire1);
        Set<Position> wirePositions2 = convertToPositions(wire2);

        Position startPosition = new Position(0, 0);

        return wirePositions1.stream()
                .filter(wirePositions2::contains)
                .mapToInt(startPosition::distance)
                .min()
                .orElseThrow();
    }

    static int part2(List<String> wire1, List<String> wire2) {
        Map<Position, Integer> wirePositions1 = convertToPositionsWithWireDistance(wire1);
        Map<Position, Integer> wirePositions2 = convertToPositionsWithWireDistance(wire2);

        return wirePositions1.entrySet().stream()
                // Filter cross points
                .filter(wirePositionEntry -> wirePositions2.containsKey(wirePositionEntry.getKey()))
                // Map cross points to the sum of both wire lengths
                .mapToInt(wirePositionEntry -> wirePositionEntry.getValue() + wirePositions2.get(wirePositionEntry.getKey()))
                .min()
                .orElseThrow();
    }

    private static Set<Position> convertToPositions(List<String> wireDirections) {
        Set<Position> positions = new HashSet<>();
        Position previousPosition = new Position(0, 0);

        for (String wireDirection : wireDirections) {
            Direction direction = Direction.valueOf(wireDirection.substring(0, 1));
            int distance = Integer.parseInt(wireDirection.substring(1));

            for (int i = 0; i < distance; i++) {
                Position position = new Position(previousPosition.getX() + direction.getX(), previousPosition.getY() + direction.getY());
                positions.add(position);
                previousPosition = position;
            }
        }

        return positions;
    }

    private static Map<Position, Integer> convertToPositionsWithWireDistance(List<String> wireDirections) {
        Map<Position, Integer> positionsWithWireDistance = new HashMap<>();
        int wireDistance = 1;
        Position previousPosition = new Position(0, 0);

        for (String wireDirection : wireDirections) {
            Direction direction = Direction.valueOf(wireDirection.substring(0, 1));
            int distance = Integer.parseInt(wireDirection.substring(1));

            for (int i = 0; i < distance; i++) {
                Position position = new Position(previousPosition.getX() + direction.getX(), previousPosition.getY() + direction.getY());
                positionsWithWireDistance.put(position, wireDistance);
                previousPosition = position;
                wireDistance++;
            }
        }

        return positionsWithWireDistance;
    }

}

@Getter
enum Direction {
    U(0, -1), D(0, 1), L(-1, 0), R(1, 0);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

@EqualsAndHashCode
@Value
@ToString
class Position {
    int x, y;

    public int distance(Position other) {
        return Math.abs(x - other.getX()) + Math.abs(y - other.getY());
    }
}
