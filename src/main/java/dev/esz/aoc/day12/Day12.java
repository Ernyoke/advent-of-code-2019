package dev.esz.aoc.day12;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Day12 {
    static int part1(List<String> lines, int steps) {
        List<Vector> moons = parseLines(lines);
        List<Vector> velocities = moons.stream()
                .map(moon -> new Vector(0, 0, 0))
                .collect(Collectors.toList());
        for (int i = 0; i < steps; i++) {
            computeVelocities(moons, velocities);
            moons = applyVelocities(moons, velocities);
        }
        int energy = 0;
        for (int i = 0; i < moons.size(); i++) {
            energy += (moons.get(i).absoluteSum() * velocities.get(i).absoluteSum());
        }
        return energy;
    }

    static long part2(List<String> lines) {
        List<Vector> moons = parseLines(lines);
        int[] x = moons.stream().mapToInt(Vector::getX).toArray();
        int[] y = moons.stream().mapToInt(Vector::getY).toArray();
        int[] z = moons.stream().mapToInt(Vector::getZ).toArray();

        return lcm(lcm(computeStepsToInitialPhase(x), computeStepsToInitialPhase(y)), computeStepsToInitialPhase(z));
    }

    private static List<Vector> parseLines(List<String> lines) {
        return lines.stream()
                .map(Day12::parseLine)
                .collect(Collectors.toUnmodifiableList());
    }

    private static Vector parseLine(String line) {
        String[] parts = line.split(", ");
        int x = Integer.parseInt(parts[0].replace("<x=", ""));
        int y = Integer.parseInt(parts[1].replace("y=", ""));
        int z = Integer.parseInt(parts[2].replace("z=", "").replace(">", ""));
        return new Vector(x, y, z);
    }

    private static void computeVelocities(List<Vector> moons, List<Vector> velocities) {
        for (int i = 0; i < moons.size(); i++) {
            Vector velocity = velocities.get(i);
            for (int j = i + 1; j < moons.size(); j++) {
                Vector moon = moons.get(i);
                Vector otherMoon = moons.get(j);
                Vector otherVelocity = velocities.get(j);
                velocity = velocity.add(new Vector(
                        compare(moon.getX(), otherMoon.getX()),
                        compare(moon.getY(), otherMoon.getY()),
                        compare(moon.getZ(), otherMoon.getZ())));
                otherVelocity = otherVelocity.add(new Vector(
                        compare(otherMoon.getX(), moon.getX()),
                        compare(otherMoon.getY(), moon.getY()),
                        compare(otherMoon.getZ(), moon.getZ())));
                velocities.set(j, otherVelocity);
            }
            velocities.set(i, velocity);
        }
    }

    private static List<Vector> applyVelocities(List<Vector> moons, List<Vector> velocities) {
        List<Vector> response = new ArrayList<>();
        for (int i = 0; i < moons.size(); i++) {
            response.add(moons.get(i).add(velocities.get(i)));
        }
        return response;
    }

    private static int compare(int a, int b) {
        if (a == b) {
            return 0;
        } else {
            if (a < b) {
                return 1;
            }
            return -1;
        }
    }

    private static long computeStepsToInitialPhase(int[] original) {
        int[] a = Arrays.copyOf(original, original.length);
        int[] b = new int[a.length];
        int[] bOriginal = Arrays.copyOf(b, b.length);
        for (int step = 1; ; step++) {
            for (int i = 0; i < a.length - 1; i++) {
                for (int j = i + 1; j < a.length; j++) {
                    if (a[i] < a[j]) {
                        b[i]++;
                        b[j]--;
                    } else {
                        if (a[i] > a[j]) {
                            b[j]++;
                            b[i]--;
                        }
                    }
                }
            }
            for (int i = 0; i < a.length; i++) {
                a[i] += b[i];
            }

            if (Arrays.equals(a, original) && Arrays.equals(b, bOriginal)) {
                return step;
            }
            step++;
        }
    }

    private static long lcm(long a, long b) {
        return Math.abs(a * b) / BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).longValue();
    }
}

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
class Vector {
    private final int x;
    private final int y;
    private final int z;

    public Vector add(Vector other) {
        return new Vector(x + other.getX(), y + other.getY(), z + other.getZ());
    }

    public int absoluteSum() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }
}
