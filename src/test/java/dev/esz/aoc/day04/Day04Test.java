package dev.esz.aoc.day04;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {
    @Test
    void part1Test1() {
        Assertions.assertThat(Day04.part1(111111, 111111)).isEqualTo(1);
    }

    @Test
    void part1Test2() {
        Assertions.assertThat(Day04.part1(223450, 223450)).isEqualTo(0);
    }


    @Test
    void part1() {
        Assertions.assertThat(Day04.part1(254032, 789860)).isEqualTo(1033);
    }

    @Test
    void part2Test1() {
        Assertions.assertThat(Day04.part2(112233, 112233)).isEqualTo(1);
    }

    @Test
    void part2Test2() {
        Assertions.assertThat(Day04.part2(123444, 123444)).isEqualTo(0);
    }

    @Test
    void part2Test3() {
        Assertions.assertThat(Day04.part2(111122, 111122)).isEqualTo(1);
    }


    @Test
    void part2() {
        Assertions.assertThat(Day04.part2(254032, 789860)).isEqualTo(670);
    }
}
