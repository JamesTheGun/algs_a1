/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */
import uq.comp3506.a1.structures.DynamicArray;

public class TestDynamicArray {

    private static void print(Object text) {
        System.out.println(text);
    }

    private static DynamicArray<Integer> createPopulatedList() {
        DynamicArray<Integer> da = new DynamicArray<>();
        for (int i = 0; i < 10; i++) {
            da.append(i);
        }
        return da;
    }

    private static void printDA(DynamicArray<Integer> da) {
        print("---------");
        for (int i = 0; i < da.size(); i++) {
            print(i + ":" + da.get(i));
        }
        print("---------");
    }

    private static void printLiteral(DynamicArray<Integer> da) {
        print("---------");
        for (int i = 0; i < da.getCapacity(); i++) {
            print(i + ":" + da.get_literal(i));
        }
        print("---------");
    }

    // ---- tiny helpers (no custom assert wrappers) ----
    private static boolean contains(DynamicArray<Integer> da, Integer x) {
        for (int i = 0; i < da.size(); i++) {
            Integer v = da.get(i);
            if (x == null ? v == null : x.equals(v)) return true;
        }
        return false;
    }
    private static int countOf(DynamicArray<Integer> da, Integer x) {
        int c = 0;
        for (int i = 0; i < da.size(); i++) {
            Integer v = da.get(i);
            if (x == null ? v == null : x.equals(v)) c++;
        }
        return c;
    }

    public static void testCreateAndSize() {
        DynamicArray<String> da = new DynamicArray<String>();
        assert da.size() == 0 : "assertion failed A1";
        // capacity should be >= 0 and literal view matches empty
        assert da.getCapacity() >= 0 : "capacity must be non-negative";
    }

    public static void testGet() {
        DynamicArray<Integer> da = createPopulatedList();
        assert da.get(0) == 0 : "assertion failed #1";
        assert da.get(1) == 1 : "assertion failed #2";
        assert da.get(2) == 2 : "assertion failed #3";
        assert da.get(8) == 8 : "assertion failed #4";
        assert da.get(9) == 9 : "assertion failed #5";

        // bounds checks
        try { da.get(-1); assert false : "get(-1) should throw"; } catch (IndexOutOfBoundsException ok) {}
        try { da.get(10); assert false : "get(10) should throw"; } catch (IndexOutOfBoundsException ok) {}
    }

    public static void testInsertion() {
        DynamicArray<Integer> da = new DynamicArray<Integer>();
        da.append(10);
        assert da.size() == 1 : "assertion failed B1";
        Integer old = da.set(0, 123);
        assert old == 10 : "assertion failed B2";
        assert da.get(0) == 123 : "assertion failed B3";

        // add at ends and middle
        da.append(5);          // [123,5]
        da.add(1, 7);          // [123,7,5]
        assert da.size() == 3 : "size after add failed";
        assert da.get(0) == 123 && da.get(1) == 7 && da.get(2) == 5 : "sequence after add wrong";

        // illegal add
        try { da.add(4, 9); assert false : "add beyond size should throw"; } catch (IndexOutOfBoundsException ok) {}
    }

    public static void testRemove() {
        DynamicArray<Integer> da = createPopulatedList();
        printDA(da);
        Integer r9 = da.remove(9); // remove 9
        Integer r3 = da.remove(3); // remove 3
        Integer r2 = da.remove(2); // remove 2
        assert r9 == 9 && r3 == 3 && r2 == 2 : "remove returns wrong values";

        print(da.get(0));
        assert da.get(0) == 0 : "assertion failed C1";
        assert da.get(1) == 1 : "assertion failed C2";
        assert da.get(2) == 4 : "assertion failed C3";
        assert da.get(4) == 6 : "assertion failed C6";
        assert da.get(6) == 8 : "assertion failed C5";
        assert da.size() == 7 : "size after removes should be 7";

        // remove bounds
        try { da.remove(7); assert false : "remove(size) should throw"; } catch (IndexOutOfBoundsException ok) {}
    }

    public static void hints() {
        System.out.println("Look at the Ed Lesson on testing!");
        System.out.println("Consider using randomness to do large scale testing!");
    }

    public static void testAdd() {
        DynamicArray<Integer> da_populated = createPopulatedList();
        da_populated.add(0, 99);
        da_populated.add(1, 88);
        da_populated.add(3, 77);
        da_populated.add(8, 66);
        da_populated.add(9, 55);
        da_populated.add(5, 44);

        assert da_populated.get(0) == 99 : "assertion failed E3";
        assert da_populated.get(3) == 77 : "assertion failed E4";
        assert da_populated.get(12) == 6 : "assertion failed E6";
        assert da_populated.get(9) == 66 : "assertion failed E6";
        assert da_populated.size() == 16 : "size should be 16 after adds";

        // ensure internal shifting is correct around insert points
        assert da_populated.get(1) == 88 : "post-insert shift check failed";
    }

    public static void testMisc() {
        DynamicArray<Integer> da = createPopulatedList();
        // da.get(10); //testing error raising
        // verify iteration order is stable
        for (int i = 0; i < da.size(); i++) {
            assert da.get(i) == i : "iteration order wrong at " + i;
        }
    }

    public static void testSet() {
        DynamicArray<Integer> da = createPopulatedList();
        Integer p0 = da.set(0, 99);
        Integer p4 = da.set(4, 88);
        Integer p9 = da.set(9, 77);

        assert p0 == 0 && p4 == 4 && p9 == 9 : "set should return previous values";

        assert da.get(0) == 99 : "assertion failed F1";
        assert da.get(4) == 88 : "assertion failed F2";
        assert da.get(8) == 8 : "assertion failed F3";
        assert da.get(9) == 77 : "assertion failed F4";

        try { da.set(102, 66); assert false : "out-of-bounds set must throw"; } catch (IndexOutOfBoundsException ok) {}
    }

    public static void testPrepend() {
        DynamicArray<Integer> da = new DynamicArray<>(); // start empty
        printLiteral(da);

        da.prepend(99);
        printLiteral(da);

        da.prepend(88);
        printLiteral(da);

        da.prepend(77);
        printLiteral(da);

        da.append(11);
        printLiteral(da);

        da.append(22);
        printLiteral(da);

        da.append(33);
        printLiteral(da);

        da.prepend(66);
        printLiteral(da);

        da.prepend(55);
        printLiteral(da);

        da.prepend(44);
        printLiteral(da);

        printDA(da);

        print("da.remove(0);");
        Integer rem0a = da.remove(0);
        print(rem0a);
        printDA(da);

        print("da.remove(3);");
        Integer rem3a = da.remove(3);
        print(rem3a);
        printDA(da);

        print("da.remove(1);");
        Integer rem1a = da.remove(1);
        print(rem1a);
        printDA(da);

        print("da.remove(0);");
        Integer rem0b = da.remove(0);
        print(rem0b);
        printDA(da);

        da.append(101);
        printLiteral(da);

        da.append(202);
        printLiteral(da);

        da.append(303);
        printLiteral(da);

        da.prepend(404);
        printLiteral(da);

        da.prepend(505);
        printLiteral(da);

        da.prepend(606);
        printLiteral(da);

        printDA(da);

        print("da.remove(0);");
        da.remove(0);
        printDA(da);

        print("da.remove(4);");
        da.remove(4);
        printDA(da);

        print("da.remove(2);");
        da.remove(2);
        printDA(da);

        da.append(707);
        printLiteral(da);

        da.append(808);
        printLiteral(da);

        da.append(909);
        printLiteral(da);

        da.prepend(111);
        printLiteral(da);

        da.prepend(222);
        printLiteral(da);

        da.prepend(333);
        printLiteral(da);

        printDA(da);

        print("da.remove(0);");
        da.remove(0);
        printDA(da);

        print("da.remove(6);");
        da.remove(6);
        printDA(da);

        print("da.remove(5);");
        da.remove(5);
        printDA(da);

        print("da.remove(4);");
        da.remove(4);
        printDA(da);

        print("da.remove(3);");
        da.remove(3);
        printDA(da);

        print("da.remove(2);");
        da.remove(2);
        printDA(da);

        print("da.remove(1);");
        da.remove(1);
        printDA(da);

        print("da.remove(0);");
        da.remove(0);
        printDA(da);

        da.append(999);
        printLiteral(da);

        da.prepend(888);
        printLiteral(da);

        da.append(777);
        printLiteral(da);

        da.prepend(666);
        printLiteral(da);

        print("da.remove(0);");
        da.remove(0);
        printDA(da);

        print("da.remove(1);");
        da.remove(1);
        printDA(da);

        print("da.remove(2);");
        da.remove(2);
        printDA(da);

        print("da.remove(3);");
        da.remove(3);
        printDA(da);

        print("da.remove(4);");
        da.remove(4);
        printDA(da);

        // Optional final assertions if you want to hardcode expected results:
        print(da.size());
        print("da.add(4,1);");
        da.add(4, 1);
        printDA(da);
        print("da.add(6,1);");
        da.add(6, 1);
        printDA(da);
        print("da.add(0,12);");
        da.add(0, 12);
        printDA(da);

        // --- append + sort + get: verify non-decreasing after sort ---
        print("da.sort();");
        printDA(da);
        print("after");
        da.sort();
        printDA(da);
        for (int i = 1; i < da.size(); i++) {
            Integer a = da.get(i - 1), b = da.get(i);
            assert (a == null || b == null) || (a <= b) : "array not sorted at i=" + i;
        }

        print("clear");
        da.clear();
        printDA(da);
        assert da.size() == 0 : "clear should set size to 0";

        print("da.append(1);");
        da.append(1);
        printDA(da);
        assert da.size() == 1 && da.get(0) == 1 : "append after clear failed";

        print("clear");
        da.clear();
        printDA(da);
        assert da.size() == 0 : "clear #2 failed";

        print("da.prepend(1);");
        da.prepend(1);
        printDA(da);
        assert da.size() == 1 && da.get(0) == 1 : "prepend into empty failed";
    }

    // ========== TEST 1: Basic duplicates / not-found ==========
    private static void testBasicRemoveFirstValue() {
        print("=== TEST 1: append + removeFirst(T) + get (basics) ===");
        DynamicArray<Integer> da = new DynamicArray<>();

        // seed: [1,2,3,2,4,2]
        da.append(1); da.append(2); da.append(3); da.append(2); da.append(4); da.append(2);
        printDA(da);
        assert da.size() == 6 : "seed size wrong";

        // 1st removeFirst(2): expect [1,3,2,4,2]
        boolean r1 = da.removeFirst(2);
        assert r1 : "removeFirst(2) should be true #1";
        assert da.size() == 5 : "size after first removeFirst wrong";
        assert da.get(0) == 1 && da.get(1) == 3 : "order after first removeFirst wrong";
        assert countOf(da, 2) == 2 : "count of '2' after first removeFirst wrong";

        // 2nd removeFirst(2): expect [1,3,4,2]
        boolean r2 = da.removeFirst(2);
        assert r2 : "removeFirst(2) should be true #2";
        assert da.size() == 4 : "size after second removeFirst wrong";
        assert da.get(0) == 1 && da.get(1) == 3 && da.get(2) == 4 : "order after second removeFirst wrong";
        assert countOf(da, 2) == 1 : "count of '2' after second removeFirst wrong";

        // removeFirst(5): false, list unchanged
        boolean r3 = da.removeFirst(5);
        assert !r3 : "removeFirst(5) should be false";
        assert da.size() == 4 : "size changed after not-found removeFirst";
        assert da.get(0) == 1 && da.get(1) == 3 && da.get(2) == 4 && da.get(3) == 2 : "content changed after not-found removeFirst";

        // spot
        assert da.get(0) == 1 && da.get(da.size() - 1) == 2 : "endpoints wrong after removes";
        printLiteral(da);
    }

    // ========== TEST 2: Wrap + removeFirst(T) across boundary ==========
    private static void testWrapThenRemoveFirstValue() {
        print("=== TEST 2: wrap + removeFirst(T) across physical boundary ===");
        DynamicArray<Integer> da = new DynamicArray<>();

        print("append 0..9");
        for (int i = 0; i < 10; i++) da.append(i);
        printDA(da);
        assert da.size() == 10 && da.get(0) == 0 && da.get(9) == 9 : "seed 0..9 failed";

        print("advance head via remove(0) x4 (simulate queue pops)");
        for (int i = 0; i < 4; i++) {
            Integer rem = da.remove(0);
            assert rem == i : "remove(0) returned wrong value";
        }
        printDA(da);
        assert da.size() == 6 && da.get(0) == 4 && da.get(5) == 9 : "after head-advance wrong";

        print("append 10..16 (will likely wrap in a circular buffer)");
        for (int i = 10; i <= 16; i++) da.append(i);
        printDA(da);
        assert da.size() == 13 : "size after wrap-append wrong";
        assert contains(da, 10) && contains(da, 16) : "wrapped values missing";

        print("da.removeFirst(12); // target in wrapped tail");
        boolean x = da.removeFirst(12);
        assert x : "removeFirst(12) should be true";
        assert !contains(da, 12) : "12 should be removed exactly once";
        assert countOf(da, 12) == 0 : "12 still present";

        print("da.removeFirst(2);  // target was in the removed prefix; expect false now");
        boolean y = da.removeFirst(2);
        assert !y : "removeFirst(2) should be false (already popped earlier)";
        assert !contains(da, 2) : "2 must not be present";

        print("da.removeFirst(15); // another wrapped element");
        boolean z = da.removeFirst(15);
        assert z : "removeFirst(15) should be true";
        assert !contains(da, 15) : "15 should be removed";
    }

    // ========== TEST 3: Growth while head != 0 + removeFirst(T) ==========
    private static void testGrowthHeadNotZero() {
        print("=== TEST 3: growth while head != 0 + removeFirst(T) ===");
        DynamicArray<Integer> da = new DynamicArray<>();

        print("append 100..107");
        for (int i = 100; i <= 107; i++) da.append(i);
        printDA(da);
        assert da.size() == 8 && da.get(0) == 100 && da.get(7) == 107 : "seed 100..107 wrong";

        print("advance head via remove(0) x5");
        for (int i = 100; i <= 104; i++) {
            Integer rem = da.remove(0);
            assert rem == i : "remove(0) wrong during advance";
        }
        printDA(da);
        assert da.size() == 3 && da.get(0) == 105 : "after advance wrong";

        print("force growth while head != 0: append 200..260");
        for (int i = 200; i <= 260; i++) da.append(i);
        print("[size=" + da.size() + "]");
        assert da.size() == (3 + (260 - 200 + 1)) : "size after growth wrong";

        // spot checks survive growth
        assert da.get(0) == 105 : "head element lost across growth";
        assert da.get(2) == 107 : "tail of original segment wrong";
        assert da.get(da.size() - 1) == 260 : "last after growth wrong";

        print("removeFirst(230), removeFirst(205), removeFirst(999) // last should be false");
        boolean r230 = da.removeFirst(230);
        assert r230 : "removeFirst(230) should be true";
        assert !contains(da, 230) : "230 should be removed";

        boolean r205 = da.removeFirst(205);
        assert r205 : "removeFirst(205) should be true";
        assert !contains(da, 205) : "205 should be removed";

        boolean r999 = da.removeFirst(999);
        assert !r999 : "removeFirst(999) should be false";
        assert !contains(da, 999) : "999 must not exist";
    }

    // ========== TEST 4: Null handling (only if your DA allows nulls) ==========
    private static void testNullsRemoveFirstValue() {
        print("=== TEST 4: null handling in removeFirst(T) ===");
        DynamicArray<Integer> da = new DynamicArray<>();

        print("append 1, null, 2, null, 3");
        da.append(1);
        da.append(null);
        da.append(2);
        da.append(null);
        da.append(3);
        printDA(da);

        // counts
        assert countOf(da, null) == 2 : "null count should be 2 initially";

        print("da.removeFirst(null); // remove first null");
        boolean a = da.removeFirst(null);
        assert a : "removeFirst(null) #1 should be true";
        assert countOf(da, null) == 1 : "one null should remain";

        print("da.removeFirst(null); // remove second null");
        boolean b = da.removeFirst(null);
        assert b : "removeFirst(null) #2 should be true";
        assert countOf(da, null) == 0 : "no nulls should remain";

        print("da.removeFirst(null); // expect false now");
        boolean c = da.removeFirst(null);
        assert !c : "removeFirst(null) #3 should be false";
        assert countOf(da, null) == 0 : "null count should stay 0";
    }

    public static void main(String[] args) {
        System.out.println("Testing DynamicArray Class...");
        testGet();
        testAdd();
        testCreateAndSize();
        testInsertion();
        testRemove();
        testMisc();
        testSet();
        testPrepend();

        // These specifically target the autograder cases:
        testBasicRemoveFirstValue();       // append + removeFirst + get
        testWrapThenRemoveFirstValue();    // wrap boundary + removeFirst
        testGrowthHeadNotZero();           // ensureCapacity while head != 0
        testNullsRemoveFirstValue();       // optional: nulls

        // Final: ensure sort is non-decreasing everywhere in current DA instances already tested above.
        System.out.println("Success!");
        assert 1 == 2 : "Assertions are working!";
    }
}
