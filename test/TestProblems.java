/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */
import uq.comp3506.a1.Problems;
public class TestProblems {

    // The series of tests that need to be implemented
    public static void testShortRuns() {
        System.out.println("Testing 'Short Runs'");
        System.out.println(Problems.shortRuns("HELLOOOO"));
        System.out.println(Problems.shortRuns("AAAAAAAA"));
        System.out.println(Problems.shortRuns("VERYSAD"));
        System.out.println(Problems.shortRuns("AAAAAAAAAAAA"));

    }

    public static void testArithmeticRules() {
        System.out.println("Testing 'Arithmetic Rules'");
    }

    public static void testSqrtHappens() {
        System.out.println("Testing 'SQRT Happens'");
        System.out.println(Problems.sqrtHappens(17, 0.001));
    }

    public static void testSpaceOddity() {
        System.out.println("Testing 'Space Oddity'");

        Long[] A7 = {8L, 8L, 8L, 1L, 2L, 2L};
        System.out.println(Problems.spaceOddity(A7) + "... expected 8");
        
        Long[] A9 = {1L, 5L, 2L, 4L, 6L, 5L, 1L, 5L, 5L, 2L, 5L};
        System.out.println(Problems.spaceOddity(A9) + "... expected 6");

        Long[] A10 = {1L, 1L, 5L, 5L, 2L, 3L, 2L, 3L};
        System.out.println(Problems.spaceOddity(A10) + "... expected -1");

        Long[] A11 = {9L, 9L, 1L, 5L, 1L, 9L, 1L, 9L};
        System.out.println(Problems.spaceOddity(A11) + "... expected 5");
    }

    public static void testLifeIsSweet() {
        System.out.println("Testing 'Life is Sweet'");

        // Trivial: eat all / eat none → no breaks
        System.out.println(Problems.lifeIsSweet(1, 1, 1) + "... expected 0");
        System.out.println(Problems.lifeIsSweet(3, 3, 0) + "... expected 0");
        System.out.println(Problems.lifeIsSweet(3, 3, 9) + "... expected 0");

        // Whole rows/columns: single break
        System.out.println(Problems.lifeIsSweet(2, 3, 3) + "... expected 9");   // cut length 3 (between rows)
        System.out.println(Problems.lifeIsSweet(4, 5, 10) + "... expected 16");  // cut length 4 (between columns)

        // Small k, best via one long cut + one short cut
        System.out.println(Problems.lifeIsSweet(2, 3, 2) + "... expected 4");    // make 2x1 strip (cut length 2)
        System.out.println(Problems.lifeIsSweet(2, 3, 5) + "... expected 5");    // isolate one 1x1 (cuts: 2 then 1)

        // Square-ish target from 3x3 → 2x2 using two cuts
        System.out.println(Problems.lifeIsSweet(3, 3, 4) + "... expected 13");   // cuts: 3 then 2

        // Better order of cuts matters; pick cheaper sequence
        System.out.println(Problems.lifeIsSweet(3, 5, 6) + "... expected 18");   // cuts: 3 then 3 (2x3 target)

        // Even split in 4x4 → 8 squares (2 rows) via one cut
        System.out.println(Problems.lifeIsSweet(4, 4, 8) + "... expected 16");   // cut length 4
    }


    // Try to call the given test based on the input
    public static void dispatch(String str) {
        switch (str.toLowerCase()) {
            case "shortruns":
                testShortRuns();
                return;
            case "arithmetic":
                testArithmeticRules();
                return;
            case "sqrt":
                testSqrtHappens();
                return;
            case "space":
                testSpaceOddity();
                return;
            case "freaky":
                testFreakyNumbers();
                return;
            case "sweet":
                testLifeIsSweet();
                return;
            default:
                throw new IllegalArgumentException("Unknown command: " + str);
        }
    }

    // Does what it says on the tin
    private static void usage() {
        System.out.println("Usage: java TestProblems <commands>");
        System.out.println("Commands:");
        System.out.println("  shortruns");
        System.out.println("  arithmetic");
        System.out.println("  sqrt");
        System.out.println("  space");
        System.out.println("  freaky");
        System.out.println("  sweet");
    }

    public static void main(String[] args) {
        testLifeIsSweet();
    }

}
