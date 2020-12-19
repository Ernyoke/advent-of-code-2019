package dev.esz.aoc.day04;

public interface Day04 {
    static int part1(int lower, int upper) {
        int counter = 0;
        for (int i = lower; i <= upper; i++) {
            counter += isValidPassword(i) ? 1 : 0;
        }
        return counter;
    }

    private static boolean isValidPassword(int password) {
        int previousDigit = password % 10;
        password /= 10;
        boolean isAdjacentDuplicatePresent = false;
        while (password > 0) {
            int currentDigit = password % 10;
            password /= 10;
            if (currentDigit == previousDigit) {
                isAdjacentDuplicatePresent = true;
            }
            if (currentDigit > previousDigit) {
                return false;
            }
            previousDigit = currentDigit;
        }
        return isAdjacentDuplicatePresent;
    }

    static int part2(int lower, int upper) {
        int counter = 0;
        for (int i = lower; i <= upper; i++) {
            counter += isValidPasswordV2(i) ? 1 : 0;
        }
        return counter;
    }

    private static boolean isValidPasswordV2(int password) {
        int previousDigit = password % 10;
        password /= 10;

        int countAdjacentDuplicates = 0;
        boolean isAdjacentDuplicatePresent = false;

        while (password > 0) {
            int currentDigit = password % 10;

            if (currentDigit == previousDigit) {
                countAdjacentDuplicates++;
            }

            if (currentDigit < previousDigit) {
                if (countAdjacentDuplicates == 1) {
                    isAdjacentDuplicatePresent = true;
                }
                countAdjacentDuplicates = 0;
            }

            if (currentDigit > previousDigit) {
                return false;
            }

            previousDigit = currentDigit;
            password /= 10;
        }

        if (countAdjacentDuplicates == 1) {
            isAdjacentDuplicatePresent = true;
        }

        return isAdjacentDuplicatePresent;
    }
}
