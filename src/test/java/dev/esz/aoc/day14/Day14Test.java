package dev.esz.aoc.day14;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day14Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day14/test1.txt");
        Assertions.assertThat(Day14.part1(lines)).isEqualTo(13312);
    }

    @Test
    void part1Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day14/test2.txt");
        Assertions.assertThat(Day14.part1(lines)).isEqualTo(180697);
    }

    @Test
    void part1Test3() {
        List<String> lines = Utils.readLines("src/main/resources/day14/test3.txt");
        Assertions.assertThat(Day14.part1(lines)).isEqualTo(2210736);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day14/input.txt");
        Assertions.assertThat(Day14.part1(lines)).isEqualTo(136771);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day14/test1.txt");
        Assertions.assertThat(Day14.part2(lines)).isEqualTo(82892753);
    }

    @Test
    void part2Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day14/test2.txt");
        Assertions.assertThat(Day14.part2(lines)).isEqualTo(5586022);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day14/input.txt");
        Assertions.assertThat(Day14.part2(lines)).isEqualTo(8193614);
    }
}
