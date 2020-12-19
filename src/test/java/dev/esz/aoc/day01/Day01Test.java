package dev.esz.aoc.day01;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day01Test {
    @Test
    void testPart1() {
        List<Long> numbers = Utils.readLongs("src/main/resources/day01/input.txt");
        Assertions.assertThat(Day01.part1(numbers)).isEqualTo(3474920);
    }

    @Test
    void testPart2Test1() {
        List<Long> numbers = List.of(1969L);
        Assertions.assertThat(Day01.part2(numbers)).isEqualTo(966);
    }

    @Test
    void testPart2() {
        List<Long> numbers = Utils.readLongs("src/main/resources/day01/input.txt");
        Assertions.assertThat(Day01.part2(numbers)).isEqualTo(5209504);
    }
}
