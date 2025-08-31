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

    public static void testCreateAndSize() {
        DynamicArray<String> da = new DynamicArray<String>();
        assert da.size() == 0 : "assertion failed A1";
    }

    public static void testGet() {
        DynamicArray<Integer> da = createPopulatedList();
        assert da.get(0) == 0: "assertion failed #1";
        assert da.get(1) == 1: "assertion failed #2";
        assert da.get(2) == 2: "assertion failed #3";
        assert da.get(8) == 8: "assertion failed #4";
        assert da.get(9) == 9: "assertion failed #5";
    }

    public static void testInsertion() {
        // You should implement many of your own tests!
        DynamicArray<Integer> da = new DynamicArray<Integer>();
        da.append(10);
        assert da.size() == 1 : "assertion failed B1";
        Integer old = da.set(0, 123);
        assert old == 10 : "assertion failed B2";
        assert da.get(0) == 123 : "assertion failed B3";
        // You should do more stuff here!
    }

    public static void testRemove() {
        DynamicArray<Integer> da = createPopulatedList();
        printDA(da);
        da.remove(9);
        da.remove(3);
        da.remove(2);
        printDA(da);
        //da.remove(10);
        print(da.get(0));
        assert da.get(0) == 0: "assertion failed C1";
        assert da.get(1) == 1: "assertion failed C2";
        assert da.get(2) == 4: "assertion failed C3";
        assert da.get(6) == 8: "assertion failed C5";
        assert da.get(4) == 6: "assertion failed C6";
    }

    public static void hints() {
        System.out.println("Look at the Ed Lesson on testing!");
        System.out.println("Consider using randomness to do large scale testing!");
    }

    public static void testAdd() {
        DynamicArray<Integer> da_populated = createPopulatedList();
        da_populated.add(0,99);
        da_populated.add(1,88);
        da_populated.add(3,77);
        da_populated.add(8,66);
        da_populated.add(9,55);
        da_populated.add(5,44);

        //da.remove(10);

        assert da_populated.get(0) == 99: "assertion failed E3";
        assert da_populated.get(3) == 77: "assertion failed E4";
        assert da_populated.get(12) == 6: "assertion failed E6";
        assert da_populated.get(9) == 66: "assertion failed E6";
    }

    public static void testMisc() {
        DynamicArray<Integer> da = createPopulatedList();
        //da.get(10); //testing error raising
    }

    public static void testSet(){
        DynamicArray<Integer> da = createPopulatedList();
        da.set(0,99);
        da.set(4,88);
        da.set(9,77);
        //da.set(102,66); // testing that this breaks

        assert da.get(0) == 99: "assertion failed F1";
        assert da.get(4) == 88: "assertion failed F2";
        assert da.get(8) == 8: "assertion failed F3";
        assert da.get(9) == 77: "assertion failed F4";
    }

    public static void testPrepend() {
        DynamicArray<Integer> da = createPopulatedList();
        da.prepend(99);
        da.prepend(88);
        da.prepend(77);
        printDA(da);
        assert da.get(0) == 77: "assertion failed G1";
        assert da.get(1) == 88: "assertion failed G2";
        assert da.get(2) == 99: "assertion failed G3";
        assert da.get(3) == 0: "assertion failed G4";
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
        System.out.println("Success!");
        assert 1==2 : "Assertions are working!";
    }
}