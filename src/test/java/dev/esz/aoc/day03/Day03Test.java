package dev.esz.aoc.day03;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {

    @Test
    void part1Test0() {
        List<List<String>> lines = Utils.processLines("src/main/resources/day03/test0.txt", ",");
        Assertions.assertThat(Day03.part1(lines.get(0), lines.get(1))).isEqualTo(6);
    }

    @Test
    void part1Test1() {
        List<List<String>> lines = Utils.processLines("src/main/resources/day03/test1.txt", ",");
        Assertions.assertThat(Day03.part1(lines.get(0), lines.get(1))).isEqualTo(159);
    }

    @Test
    void part1Test2() {
        List<List<String>> lines = Utils.processLines("src/main/resources/day03/test2.txt", ",");
        Assertions.assertThat(Day03.part1(lines.get(0), lines.get(1))).isEqualTo(135);
    }

    @Test
    void part1() {
        List<List<String>> lines = Utils.processLines("src/main/resources/day03/input.txt", ",");
        Assertions.assertThat(Day03.part1(lines.get(0), lines.get(1))).isEqualTo(870);
    }

    @Test
    void part2() {
        List<List<String>> lines = Utils.processLines("src/main/resources/day03/input.txt", ",");
        Assertions.assertThat(Day03.part2(lines.get(0), lines.get(1))).isEqualTo(13698);
    }
}
