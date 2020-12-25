package dev.esz.aoc.day07;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day07Test {

    @Test
    void part1Test1() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day07/test1.txt");
        Assertions.assertThat(Day07.part1(numbers, List.of(4, 3, 2, 1, 0))).isEqualTo(43210);
    }

    @Test
    void part1Test2() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day07/test2.txt");
        Assertions.assertThat(Day07.part1(numbers, List.of(0, 1, 2, 3, 4))).isEqualTo(54321);
    }

    @Test
    void part1() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day07/input.txt");
        Assertions.assertThat(Day07.part1(numbers)).isEqualTo(914828);
    }

    @Test
    void part2Test1() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day07/test3.txt");
        Assertions.assertThat(Day07.part2(numbers, List.of(9, 8, 7, 6, 5))).isEqualTo(139629729);
    }

    @Test
    void part2Test2() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day07/test4.txt");
        Assertions.assertThat(Day07.part2(numbers, List.of(9, 7, 8, 5, 6))).isEqualTo(18216);
    }

    @Test
    void part2Test3() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day07/test3.txt");
        Assertions.assertThat(Day07.part2(numbers)).isEqualTo(139629729);
    }

    @Test
    void part2Test4() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day07/test4.txt");
        Assertions.assertThat(Day07.part2(numbers)).isEqualTo(18216);
    }

    @Test
    void part2() {
        List<Integer> numbers = Utils.readSingleLineToInts("src/main/resources/day07/input.txt");
        Assertions.assertThat(Day07.part2(numbers)).isEqualTo(17956613);
    }
}
