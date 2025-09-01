/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import uq.comp3506.a1.structures.BitVector;

public class TestBitVector {

    // --- tiny printers to match your style ---
    private static void print(Object o) { System.out.println(o); }

    private static void printBV(BitVector bv, int limit) {
        int L = (int) Math.min(limit, Math.max(0, bv.size()));
        print("---------");
        for (int i = 0; i < L; i++) {
            print(i + ":" + bv.get(i));
        }
        if (bv.size() > L) {
            print("... (" + (bv.size() - L) + " more bits not shown) ...");
        }
        print("---------");
    }

    private static void printSlice(BitVector bv, int from, int to) {
        int a = Math.max(0, from);
        int b = Math.min((int) bv.size(), Math.max(from, to));
        StringBuilder sb = new StringBuilder();
        sb.append(a).append("..").append(b).append(": ");
        for (int i = a; i < b; i++) sb.append(bv.get(i) ? '1' : '0');
        print(sb.toString());
    }

    private static void setRange(BitVector bv, int lo, int hi) {
        for (int i = lo; i <= hi; i++) bv.set(i);
    }

    // ================== TESTS ==================

    private static void testCreateSizeCapacity() {
        print("=== TEST 0: size & capacity ===");
        BitVector bv = new BitVector(130);
        print("size() -> " + bv.size());
        print("capacity() -> " + bv.capacity());
        printBV(bv, 16);
    }

    private static void testSetGetUnsetBoundaries() {
        print("=== TEST 1: set/get/unset across word boundaries ===");
        BitVector bv = new BitVector(130);

        print("set 0, 1, 63, 64, 127");
        bv.set(0); bv.set(1); bv.set(63); bv.set(64); bv.set(127);
        printBV(bv, 132);

        print("get(0), get(1), get(63), get(64), get(127):");
        print(bv.get(0));
        print(bv.get(1));
        print(bv.get(63));
        print(bv.get(64));
        print(bv.get(127));

        print("unset(1), unset(64)");
        bv.unset(1); bv.unset(64);
        printBV(bv, 132);
    }

    private static void testComplement() {
        print("=== TEST 2: complement (flip all bits, then flip again) ===");
        BitVector bv = new BitVector(32);
        setRange(bv, 2, 3);
        setRange(bv, 8, 15);
        setRange(bv, 30, 30);

        print("before");
        printBV(bv, 32);

        print("complement()");
        bv.complement();
        printBV(bv, 32);

        print("complement() again");
        bv.complement();
        printBV(bv, 32);
    }

    private static void testShift() {
        print("=== TEST 3: shift left/right (small/large, word-crossing) ===");
        BitVector bv = new BitVector(128);

        print("pattern: set 5..9 and 60..66");
        setRange(bv, 5, 9);
        setRange(bv, 60, 66);
        printBV(bv, 96);

        print("shift(+1)");
        bv.shift(1);
        printBV(bv, 96);

        print("shift(+3)");
        bv.shift(3);
        printBV(bv, 96);

        print("shift(+65)");
        bv.shift(65);
        printBV(bv, 128);

        print("shift(-70)");
        bv.shift(-70);
        printBV(bv, 96);

        print("slices:");
        printSlice(bv, 0, 40);
        printSlice(bv, 56, 96);
    }

    private static void testRotate() {
        print("=== TEST 4: rotate (wrap-around, +/- dist, > size) ===");
        BitVector bv = new BitVector(64);

        setRange(bv, 0, 2);    // head run
        setRange(bv, 8, 8);    // single
        setRange(bv, 31, 31);  // mid spike
        setRange(bv, 60, 63);  // tail block

        print("initial");
        printBV(bv, 64);

        print("rotate(+1)");
        bv.rotate(1);
        printBV(bv, 64);

        print("rotate(+64) // modulo size → expect same as before");
        bv.rotate(64);
        printBV(bv, 64);

        print("rotate(+129) // 129 ≡ 1");
        bv.rotate(129);
        printBV(bv, 64);

        print("rotate(-5)");
        bv.rotate(-5);
        printBV(bv, 64);
    }

    private static void testPopcountRuncount() {
        print("=== TEST 5: popcount & runcount (if implemented) ===");
        BitVector bv = new BitVector(128);

        // alternating blocks to make counts obvious
        setRange(bv, 0, 15);    // 16 ones
        setRange(bv, 32, 47);   // 16 ones
        setRange(bv, 64, 71);   // 8 ones
        setRange(bv, 96, 127);  // 32 ones
        printSlice(bv, 0, 64);
        printSlice(bv, 64, 128);

        print("popcount()");
        print(bv.popcount());   // current class returns -1; this prints whatever it returns

        print("runcount() // longest run of 1s");
        print(bv.runcount());   // current class returns -1; this prints whatever it returns
    }

    private static void testOutOfBounds() {
        print("=== TEST 6: out-of-bounds behavior (should throw) ===");
        BitVector bv = new BitVector(33);

        try { print("get(-1)"); print(bv.get(-1)); } catch (Exception e) { print(e); }
        try { print("get(33)"); print(bv.get(33)); } catch (Exception e) { print(e); }

        try { print("set(-1)"); bv.set(-1); } catch (Exception e) { print(e); }
        try { print("set(33)"); bv.set(33); } catch (Exception e) { print(e); }

        try { print("unset(-1)"); bv.unset(-1); } catch (Exception e) { print(e); }
        try { print("unset(33)"); bv.unset(33); } catch (Exception e) { print(e); }
    }

    public static void main(String[] args) {
        System.out.println("Testing BitVector Class...");

        testCreateSizeCapacity();
        testSetGetUnsetBoundaries();
        testComplement();
        testShift();
        testRotate();
        testPopcountRuncount();
        testOutOfBounds();

        System.out.println("Success!");
        // Uncomment if you want to verify JVM assertions are off/on
        // assert 1==2 : "Assertions are working!";
    }
}
