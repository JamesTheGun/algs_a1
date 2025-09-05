/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import uq.comp3506.a1.structures.BitVector;

public class TestBitVector {

    // ---------- tiny printers ----------
    private static void print(Object o) {
        System.out.println(o);
    }

    private static void printBV(BitVector bv, int limit) {
        final int BITS_PER_LINE = 8; // adjust to taste

        int size = (int) bv.size();
        int L = Math.min(limit, Math.max(0, size));

        System.out.println("---------");

        for (int i = 0; i < L; i++) {
            // Print index:value
            System.out.printf("%2d:%s  ", i, bv.get(i) ? "1" : "0");

            // Line break every BITS_PER_LINE elements
            if ((i + 1) % BITS_PER_LINE == 0) {
                System.out.println();
            }
        }

        if (L % BITS_PER_LINE != 0) {
            System.out.println();
        }

        if (size > L) {
            System.out.println("... (" + (size - L) + " more bits not shown) ...");
        }

        System.out.println("---------");
    }


    private static void printSlice(BitVector bv, int from, int to) {
        int a = Math.max(0, from);
        int b = Math.min((int) bv.size(), Math.max(from, to));
        StringBuilder sb = new StringBuilder();
        sb.append(a).append("..").append(b).append(": ");
        for (int i = a; i < b; i++)
            sb.append(bv.get(i) ? '1' : '0');
        print(sb.toString());
    }

    private static void setRange(BitVector bv, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            bv.set(i);
    }

    // ---------- assert helpers ----------
    private static void assertTrue(boolean cond, String msg) {
        assert cond : msg;
    }

    private static void assertFalse(boolean cond, String msg) {
        assert !cond : msg;
    }

    private static void assertEq(long a, long b, String msg) {
        assert a == b : msg + " (got " + a + ", want " + b + ")";
    }

    private static void assertEq(int a, int b, String msg) {
        assert a == b : msg + " (got " + a + ", want " + b + ")";
    }

    private static void assertEq(boolean a, boolean b, String msg) {
        assert a == b : msg + " (got " + a + ", want " + b + ")";
    }

    @FunctionalInterface
    private interface Throwing {
        void run() throws Exception;
    }

    private static void assertThrows(Class<? extends Throwable> type, Throwing fn, String msg) {
        boolean threw = false;
        try {
            fn.run();
        } catch (Throwable t) {
            threw = true;
            assertTrue(type.isInstance(t), msg + " (threw " + t.getClass().getSimpleName()
                    + ", expected " + type.getSimpleName() + ")");
        }
        assertTrue(threw, msg + " (did not throw)");
    }

    // ================== TESTS ==================

    private static void testCreateSizeCapacity() {
        print("=== TEST 0: size & capacity ===");
        BitVector bv = new BitVector(130);
        print("size() -> " + bv.size());
        print("capacity() -> " + bv.capacity());
        printBV(bv, 16);

        // assertions
        assertEq((int) bv.size(), 130, "size mismatch");
        // capacity should be >= size, typically rounded to a multiple of 64
        assertTrue(bv.capacity() >= 130, "capacity must be >= size");
        assertEq((int) (bv.capacity() % 64), 0, "capacity should be a multiple of 64");
        // brand-new BV must be all zeros
        for (int i = 0; i < bv.size(); i++) {
            assertFalse(bv.get(i), "fresh bit should be 0 at " + i);
        }
    }

    private static void testSetGetUnsetBoundaries() {
        print("=== TEST 1: set/get/unset across word boundaries ===");
        BitVector bv = new BitVector(130);

        print("set 0, 1, 63, 64, 127");
        bv.set(0);
        bv.set(1);
        bv.set(63);
        bv.set(64);
        bv.set(127);
        printBV(bv, 10);

        // gets
        assertTrue(bv.get(0), "bit 0 should be 1");
        assertTrue(bv.get(1), "bit 1 should be 1");
        assertTrue(bv.get(63), "bit 63 should be 1");
        assertTrue(bv.get(64), "bit 64 should be 1");
        assertTrue(bv.get(127), "bit 127 should be 1");
        // some neighbors remain 0
        assertFalse(bv.get(2), "bit 2 should be 0");
        assertFalse(bv.get(62), "bit 62 should be 0");
        assertFalse(bv.get(65), "bit 65 should be 0");

        print("unset(1), unset(64)");
        bv.unset(1);
        bv.unset(64);
        assertTrue(bv.get(0), "bit 0 should stay 1");
        assertFalse(bv.get(1), "bit 1 should be 0 after unset");
        assertTrue(bv.get(63), "bit 63 should stay 1");
        assertFalse(bv.get(64), "bit 64 should be 0 after unset");
        assertTrue(bv.get(127), "bit 127 should stay 1");
    }

    private static void testComplement() {
        print("=== TEST 2: complement (flip all bits, then flip again) ===");
        BitVector bv = new BitVector(32);
        setRange(bv, 2, 3);
        setRange(bv, 8, 15);
        setRange(bv, 30, 30);

        // pre-check a few sentinels
        assertTrue(bv.get(2) && bv.get(3), "2..3 must be set");
        assertTrue(bv.get(8) && bv.get(15), "8 and 15 must be set");
        assertTrue(bv.get(30), "30 must be set");
        assertFalse(bv.get(0) || bv.get(1), "0..1 must be clear");
        assertFalse(bv.get(16), "16 must be clear");
        assertFalse(bv.get(31), "31 must be clear");

        print("complement()");
        bv.complement();
        // after complement, previous ones become zeros and some chosen zeros become ones
        assertFalse(bv.get(2) || bv.get(3), "2..3 should flip to 0");
        assertFalse(bv.get(8) || bv.get(15), "8/15 should flip to 0");
        assertFalse(bv.get(30), "30 should flip to 0");
        assertTrue(bv.get(0) && bv.get(1), "0..1 should flip to 1");
        assertTrue(bv.get(16), "16 should flip to 1");
        assertTrue(bv.get(31), "31 should flip to 1");

        print("complement() again");
        BitVector snapshot = cloneBV(bv); // keep to compare after double flip
        bv.complement();
        printBV(bv, 32);

        // double complement returns to original
        assertTrue(bv.get(2) && bv.get(3), "2..3 should be restored");
        assertTrue(bv.get(8) && bv.get(15), "8/15 should be restored");
        assertTrue(bv.get(30), "30 should be restored");
        assertFalse(bv.get(0) || bv.get(1), "0..1 should be restored to 0");
        assertFalse(bv.get(16), "16 should be restored to 0");
        assertFalse(bv.get(31), "31 should be restored to 0");

        // sanity: complementing snapshot should equal current
        snapshot.complement();
        for (int i = 0; i < bv.size(); i++) {
            assertEq(bv.get(i), snapshot.get(i), "double-flip equality at " + i);
        }
    }

    private static void testShift() {
        print("=== TEST 3: shift left/right (small/large, word-crossing) ===");
        BitVector bv = new BitVector(128);

        print("pattern: set 5..9 and 60..66");
        setRange(bv, 5, 9);
        setRange(bv, 60, 66);

        int initialOnes = maybePopcount(bv);
        printBV(bv, 96);

        print("shift(+1)");
        bv.shift(1);
        printBV(bv, 96);
        assertTrue(!bv.get(5) && bv.get(6), "block 5..9 should move right by 1");
        assertEq(maybePopcount(bv), initialOnes, "popcount invariant under shift (within bounds)");

        print("shift(+3)");
        bv.shift(3);
        printBV(bv, 96);
        assertTrue(bv.get(10),
                "left block should now start at 10 (original 6 + 3 -> 9; prior +1 already applied)");
        assertEq(maybePopcount(bv), initialOnes, "popcount invariant under shift (within bounds)");

        print("shift(+65)");
        bv.shift(65);
        printBV(bv, 128);
        // everything near the left should now have moved far right; left area mostly zeros
        for (int i = 0; i < 10; i++)
            assertFalse(bv.get(i), "low prefix should be zeroed after big right shift");

        print("shift(-70)");
        bv.shift(-70);
        printBV(bv, 96);
        // shifting back left shouldn’t produce ones outside [0, size)
        for (int i = 120; i < 128; i++)
            assertFalse(bv.get(i), "high tail should be zeros after left shift");
        assertEq(maybePopcount(bv), initialOnes,
                "popcount invariant across opposing shifts (within bounds)");
    }

    private static void testRotate() {
        print("=== TEST 4: rotate (wrap-around, +/- dist, > size) ===");
        BitVector bv = new BitVector(64);

        setRange(bv, 0, 2); // head run
        setRange(bv, 8, 8); // single
        setRange(bv, 31, 31); // mid spike
        setRange(bv, 60, 63); // tail block

        int ones = maybePopcount(bv);

        print("initial");
        printBV(bv, 64);
        BitVector base = cloneBV(bv);

        print("rotate(+1)");
        bv.rotate(1);
        printBV(bv, 64);
        assertTrue(bv.get(1), "bit 0 should appear at 1 after +1");
        // assertEq(maybePopcount(bv), ones, "rotate preserves popcount");

        print("rotate(+64) // modulo size → expect same as before");
        BitVector before = cloneBV(bv);
        bv.rotate(64);
        printBV(bv, 64);
        for (int i = 0; i < 64; i++)
            assertEq(bv.get(i), before.get(i), "rotate by size is identity");

        print("rotate(+129) // 129 ≡ 1");
        bv.rotate(129);
        printBV(bv, 64);
        // Equivalent to another +1 from previous state
        for (int i = 0; i < 64; i++) {
            assertEq(bv.get(i), shiftFrom(before, +1).get(i), "rotate +129 equals +1");
        }

        print("rotate(-5)");
        bv.rotate(-5);
        printBV(bv, 64);
        // Net effect since "before": +64 ≡ 0, +129 ≡ +1, then -5 => -4
        BitVector expected = shiftFrom(before, -4);
        for (long i = 0; i < 64; i++) {
            assertEq(bv.get(i), expected.get(i), "net rotate (+64,+129,-5) ≡ -4");
        }

        // rotate back to base
        bv.rotate(+3);
        for (long i = 0; i < 64; i++)
            assertEq(bv.get(i), base.get(i), "rotating back recovers base");
    }

    private static void testOutOfBounds() {
        print("=== TEST 6: out-of-bounds behavior (should throw) ===");
        BitVector bv = new BitVector(33);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            print("get(-1)");
            bv.get(-1);
        }, "get(-1) should throw");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            print("get(33)");
            bv.get(33);
        }, "get(33) should throw");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            print("set(-1)");
            bv.set(-1);
        }, "set(-1) should throw");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            print("set(33)");
            bv.set(33);
        }, "set(33) should throw");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            print("unset(-1)");
            bv.unset(-1);
        }, "unset(-1) should throw");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            print("unset(33)");
            bv.unset(33);
        }, "unset(33) should throw");
    }

    // ================== tiny BV utilities for rotate expectations ==================
    private static BitVector cloneBV(BitVector src) {
        BitVector out = new BitVector(src.size());
        for (int i = 0; i < src.size(); i++)
            if (src.get(i))
                out.set(i);
        return out;
    }

    // build expected rotate result by copying bits mathematically
    private static BitVector shiftFrom(BitVector base, int rot) {
        int n = (int) base.size();
        BitVector out = new BitVector(n);
        int r = ((rot % n) + n) % n; // normalize to [0,n)
        for (int i = 0; i < n; i++) {
            // rotate(+r): bit at i moves to (i+r) mod n
            if (base.get(i)) {
                int j = (i + r) % n;
                out.set(j);
            }
        }
        return out;
    }

    // popcount helper that tolerates unimplemented (-1)
    // popcount helper that tolerates unimplemented (-1)
    private static int maybePopcount(BitVector bv) {
        long p = bv.popcount(); // accept long
        if (p >= 0)
            return (int) p; // cast once (tests expect int)

        int count = 0;
        int n = (int) bv.size(); // if size() is long, narrow once
        for (int i = 0; i < n; i++) {
            if (bv.get(i))
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Testing BitVector Class...");

        testCreateSizeCapacity();
        testSetGetUnsetBoundaries();
        testComplement();
        testShift();
        testRotate();
        testOutOfBounds();

        System.out.println("Success!");
        // Tip: run with assertions enabled: java -ea TestBitVector
        // assert 1==2 : "Assertions are working!";
    }
}
