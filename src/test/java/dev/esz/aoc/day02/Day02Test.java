package dev.esz.aoc.day02;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day02Test {

    @Test
    void executionResultTest() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day02/test.txt");
        Assertions.assertThat(Day02.getExecutionResult(numbers.stream().mapToInt(i -> i).toArray())).isEqualTo(3500);
    }

    @Test
    void part1() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day02/input.txt");
        Assertions.assertThat(Day02.part1(numbers)).isEqualTo(3790645);
    }

    @Test
    void part2() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day02/input.txt");
        Assertions.assertThat(Day02.part2(numbers)).isEqualTo(6577);
    }
}
