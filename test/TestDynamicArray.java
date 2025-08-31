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
        DynamicArray<Integer> da = new DynamicArray<>();  // start empty
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
        print(da.remove(0));
        printDA(da);

        print("da.remove(3);");
        print(da.remove(3));
        printDA(da);

        print("da.remove(1);");
        print(da.remove(1));
        printDA(da);

        print("da.remove(0);");
        print(da.remove(0));
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
        print(da.remove(0));
        printDA(da);

        print("da.remove(4);");
        print(da.remove(4));
        printDA(da);

        print("da.remove(2);");
        print(da.remove(2));
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
        print(da.remove(0));
        printDA(da);

        print("da.remove(6);");
        print(da.remove(6));
        printDA(da);

        print("da.remove(5);");
        print(da.remove(5));
        printDA(da);

        print("da.remove(4);");
        print(da.remove(4));
        printDA(da);

        print("da.remove(3);");
        print(da.remove(3));
        printDA(da);

        print("da.remove(2);");
        print(da.remove(2));
        printDA(da);

        print("da.remove(1);");
        print(da.remove(1));
        printDA(da);

        print("da.remove(0);");
        print(da.remove(0));
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
        print(da.remove(0));
        printDA(da);

        print("da.remove(1);");
        print(da.remove(1));
        printDA(da);

        print("da.remove(2);");
        print(da.remove(2));
        printDA(da);

        print("da.remove(3);");
        print(da.remove(3));
        printDA(da);

        print("da.remove(4);");
        print(da.remove(4));
        printDA(da);

        // Optional final assertions if you want to hardcode expected results:
        print(da.size());
        print("da.add(4,1);");
        //print("da.append(1);");
        da.add(4, 1);
        //da.append(1);
        printDA(da);
        print("da.add(6,1);");
        da.add(6, 1);
        printDA(da);
        print("da.add(0,12);");
        da.add(0, 12);
        printDA(da);
        print("clear");
        da.clear();
        printDA(da);
        print("da.append(1);");
        da.append(1);
        printDA(da);
        print("clear");
        da.clear();
        printDA(da);
        print("da.prepend(1);");
        da.prepend(1);
        printDA(da);
        da.add(2,1);
        // assert da.get(0) == EXPECTED_VALUE : "Value mismatch!";
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