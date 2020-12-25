package dev.esz.aoc.day05;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day05Test {

    @Test
    void part1Test1() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test1.txt");
        Assertions.assertThat(Day05.part1(numbers, 1)).isEqualTo(0);
    }

    @Test
    void part1() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/input.txt");
        Assertions.assertThat(Day05.part1(numbers, 1)).isEqualTo(13087969);
    }

    @Test
    void part2Test1() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test2.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(8))).isEqualTo(1);
    }

    @Test
    void part2Test2() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test2.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(1))).isEqualTo(0);
    }

    @Test
    void part2Test3() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test3.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(8))).isEqualTo(1);
    }


    @Test
    void part2Test4() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test3.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(2))).isEqualTo(0);
    }

    @Test
    void part2Test5() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test4.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(2))).isEqualTo(1);
    }

    @Test
    void part2Test6() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test5.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(2))).isEqualTo(1);
    }

    @Test
    void part2Test7() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test6.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(0))).isEqualTo(0);
    }

    @Test
    void part2Test8() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/test6.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(7))).isEqualTo(1);
    }

    @Test
    void part2() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day05/input.txt");
        Assertions.assertThat(Day05.part2(numbers, List.of(5))).isEqualTo(14110739);
    }
}
