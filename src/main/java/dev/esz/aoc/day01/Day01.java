package dev.esz.aoc.day01;

import java.util.List;

public interface Day01 {
    static long part1(List<Long> massValues) {
        return massValues.stream().mapToLong(Day01::getFuelValue).sum();
    }

    static long part2(List<Long> massValues) {
        return massValues.stream().mapToLong(Day01::getAdditionalFuelValue).sum();
    }

    private static long getFuelValue(long mass) {
        return (mass / 3) - 2;
    }

    private static long getAdditionalFuelValue(long mass) {
        long fuelValue = getFuelValue(mass);
        long sum = 0;
        while (fuelValue > 0) {
            sum += fuelValue;
            fuelValue = getFuelValue(fuelValue);
        }
        return sum;
    }
}
