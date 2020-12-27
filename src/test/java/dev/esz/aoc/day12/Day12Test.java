package dev.esz.aoc.day12;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day12Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day12/test1.txt");
        Assertions.assertThat(Day12.part1(lines, 10)).isEqualTo(179);
    }

    @Test
    void part1Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day12/test2.txt");
        Assertions.assertThat(Day12.part1(lines, 100)).isEqualTo(1940);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day12/input.txt");
        Assertions.assertThat(Day12.part1(lines, 1000)).isEqualTo(179);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day12/test1.txt");
        Assertions.assertThat(Day12.part2(lines)).isEqualTo(2772);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day12/input.txt");
        Assertions.assertThat(Day12.part2(lines)).isEqualTo(314917503970904L);
    }

}
