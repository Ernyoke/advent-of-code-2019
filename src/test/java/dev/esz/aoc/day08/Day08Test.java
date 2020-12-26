package dev.esz.aoc.day08;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day08Test {

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day08/input.txt");
        Assertions.assertThat(Day08.part1(lines.get(0))).isEqualTo(1452L);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day08/input.txt");
        Day08.part2(lines.get(0));
    }
}
