// @edu:student-assignment

package uq.comp3506.a1;

// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */
public class Problems {

    /**
     * Return a string representing the RLE of input
     * <p>
     * Bounds: - Basic tests input will have up to 10 characters - Advanced tests input will have up
     * to 1000 characters - Welcome to COMP3506 input will have up to 100'000 characters
     */
    public static String shortRuns(String input) {
        int maxConcurent = 9;
        int numberSeen = 1;
        int outputCharacterCount = 0;
        char[] output = new char[input.length() * 2];
        char workingChar = input.charAt(0);

        int i;
        for (i = 1; i < input.length(); i++) {
            char thisChar = input.charAt(i);
            if (thisChar != workingChar || numberSeen >= maxConcurent) {
                output[outputCharacterCount++] = workingChar;
                output[outputCharacterCount++] = (char) ('0' + numberSeen);
                workingChar = thisChar;
                numberSeen = 1;
            } else {
                numberSeen++;
            }
        }
        output[outputCharacterCount++] = workingChar;
        output[outputCharacterCount++] = (char) ('0' + numberSeen);

        return new String(output, 0, outputCharacterCount);
    }

    /**
     * Return the maximum score that can be achieved within exactly "turns" turns - values in array
     * are guaranteed to be >= 0
     * <p>
     * Bounds: - Basic tests array will consist of up to 100 elements Each element will be up to 100
     * There will be up to 10 turns - Advanced tests array will consist of up to 10'000 elements
     * Each element will be up to 10'000 There will be up to 10'000 turns - Welcome to COMP3506
     * array will consist of up to 100'000 elements Each element will be up to 10'000'000 There will
     * be up to 10'000'000 turns
     */
    public static long arithmeticRules(Long[] array, long turns) {
        long max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        long score = 0;
        for (int i = 0; i < turns; i++) {
            score += max + i;
        }
        return score;
    }

    /**
     * Return the epsilon-approximate square root of "number" - epsilon will be in [0.00001, 1]
     * <p>
     * Bounds: - Basic tests number will be up to 1000 - Advanced tests number will be p to
     * 1'000'000 - Welcome to COMP3506 number will be up to 10**16 (ten to the power 16)
     */

    public static double sqrtHappens(long number, double epsilon) {
        double delta = number;
        double guess = 1.0;
        double this_result;
        epsilon = epsilon*0.1;

        double lo = 0.0;
        double hi = 1.0;
        while (hi * hi < (double) number) {
            hi *= 2.0;
        }
        while (true) {
            guess = (lo + hi) * 0.5;
            this_result = guess * guess;
            delta = this_result - (double) number;

            if (delta > 0.0) {
                hi = guess;
            } else {
                lo = guess;
            }
            double adelta = (delta < 0.0) ? -delta : delta;
            double width  = hi - lo;
            if (adelta <= epsilon || width <= epsilon * 0.5) {
                double formated_guess = (guess < 0.0) ? -guess : guess;
                formated_guess = Math.round(formated_guess / epsilon) * epsilon;
                return formated_guess;
            }
        }
    }

    /**
     * Return the largest integer in numbers repeated an odd number of times - values in "numbers"
     * will be in the range [0, 2^32 - 1]
     * <p>
     * Bounds: - Basic tests There will be up to 100 numbers in the array - Advanced tests There
     * will be up to 10'000 numbers in the array - Welcome to COMP3506 There will be up to 100'000
     * numbers in the array
     */

    public static long spaceOddity(Long[] numbers) {
        if (numbers.length == 0) {
            return -1;
        }
        while (true) {
            long max = Long.MIN_VALUE;//set to smallest possible long value...
            int count = 0;
            for (long num : numbers) {
                if (num > max) {
                    max = num;
                }
            }
            if (max == Long.MIN_VALUE) {
                return -1; // no numbers left
            }
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] == max) {
                    count++;
                    numbers[i] = Long.MIN_VALUE;
                }
            }
            if (count % 2 == 1) {
                return max;
            }
        }
    }

    /**
     * Return the number of k-freaky numbers in the range [m, n]
     * <p>
     * Bounds: - Basic tests m and n will be up to 1000 k will be up to 10 - Advanced tests m and n
     * will be up to 1'000'000 k will be up to 100 - Welcome to COMP3506 m and n will be up to
     * 10**15 (ten to the power 15) k will be up to 10'000
     */
    public static long freakyNumbers(long m, long n, long k) {
        return -1;
    }

    private static int[] getCloseToSquareFactorPair(int number, int limitM, int limitN) {
        int start = (int) Math.floor(sqrtHappens(((long) number), 0.1D));
        if (start < 1) start = 1;

        for (int i = start; i >= 1; i--) {
            if (number % i == 0) {
                int factorA = i;
                int factorB = number / i;
                if (factorA <= limitM && factorB <= limitN) {
                    return new int[]{factorA, factorB};
                }
            }
        }
        return new int[]{1, number};
    }

    private static long getScoreFromSplitingOnce(int m, int n, int k) {
        if (k == 0 || k == m * n) return 0;
        if (k % n == 0) {
            int rows = k / n;
            if (rows >= 1 && rows <= m) return 1L * n * n;
        }
        if (k % m == 0) {
            int cols = k / m;
            if (cols >= 1 && cols <= n) return 1L * m * m;
        }
        return -1;
    }

     /**
     * Return the optimal (minimum) cost of breaking the chocolate
     * <p>
     * Bounds: - Basic tests m and n will be up to 5 k will be up to 25 - Advanced tests m and n
     * will be up to 5 k will be up to 25 (bounds are the same but test cases are more difficult) -
     * Welcome to COMP3506 m and n will be up to 10 k will be up to 100
     */

    public static long lifeIsSweet(int m, int n, int k) {
        if (k == 0 || k == m * n) return 0;

        long scoreFromSplitingOnce = getScoreFromSplitingOnce(m, n, k); //we will always need to split along the longer access for two splits to do it optimally so we can assume its better than splitting twice, always
        if (scoreFromSplitingOnce != -1) {
            return scoreFromSplitingOnce;
        }

        int[] factors = getCloseToSquareFactorPair(k, m, n); //at most we need to use two splits
        int factorA = factors[0], factorB = factors[1];

        if (factorA > m || factorB > n) {
            int temp = factorA;
            factorA = factorB;
            factorB = temp;
        }

        long costRowsThenCols = (long)(n * n + factorA * factorA);
        long costColsThenRows = (long)(m * m + factorB * factorB);
        return Math.min(costRowsThenCols, costColsThenRows);
    }
}
