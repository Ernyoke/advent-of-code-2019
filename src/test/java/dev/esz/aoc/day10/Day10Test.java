package dev.esz.aoc.day10;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day10Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day10/test1.txt");
        Assertions.assertThat(Day10.part1(lines)).isEqualTo(8);
    }

    @Test
    void part1Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day10/test2.txt");
        Assertions.assertThat(Day10.part1(lines)).isEqualTo(33);
    }


    @Test
    void part1Test3() {
        List<String> lines = Utils.readLines("src/main/resources/day10/test3.txt");
        Assertions.assertThat(Day10.part1(lines)).isEqualTo(210);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day10/input.txt");
        Assertions.assertThat(Day10.part1(lines)).isEqualTo(309);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day10/test3.txt");
        Assertions.assertThat(Day10.part2(lines)).isEqualTo(802);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day10/input.txt");
        Assertions.assertThat(Day10.part2(lines)).isEqualTo(416);
    }
}
