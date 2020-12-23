package dev.esz.aoc.day06;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day06Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day06/test1.txt");
        Assertions.assertThat(Day06.part1(lines)).isEqualTo(42);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day06/input.txt");
        Assertions.assertThat(Day06.part1(lines)).isEqualTo(142497);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day06/test2.txt");
        Assertions.assertThat(Day06.part2(lines)).isEqualTo(4);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day06/input.txt");
        Assertions.assertThat(Day06.part2(lines)).isEqualTo(301);
    }
}
