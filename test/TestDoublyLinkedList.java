/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

// you may like to use JUnit testing - please see the Ed Lessons on
// testing for more information.

// Import the DLL
/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

// you may like to use JUnit testing - please see the Ed Lessons on
// testing for more information.

// Import the DLL
import uq.comp3506.a1.structures.DoublyLinkedList;

public class TestDoublyLinkedList {

    private static void print(Object text) {
        System.out.println(text);
    }

    private static DoublyLinkedList<Integer> createPopulatedList() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();
        dll.append(0);
        dll.append(1);
        dll.append(2);
        dll.append(3);
        dll.append(4);
        dll.append(5);
        dll.append(6);
        dll.append(7);
        dll.append(8);
        dll.append(9);
        return dll;
    }

    private static void print_ddl(DoublyLinkedList<Integer> dll_populated) {
        //yeah could not care less at this point
        System.out.print("0:");
        print(dll_populated.get(0));
        System.out.print("1:");
        print(dll_populated.get(1));
        System.out.print("2:");
        print(dll_populated.get(2));
        System.out.print("3:");
        print(dll_populated.get(3));
        System.out.print("4:");
        print(dll_populated.get(4));
        System.out.print("5:");
        print(dll_populated.get(5));
        System.out.print("6:");
        print(dll_populated.get(6));
        System.out.print("7:");
        print(dll_populated.get(7));
        System.out.print("8:");
        print(dll_populated.get(8));
        System.out.print("9:");
        print(dll_populated.get(9));
        System.out.print("10:");
        print(dll_populated.get(10));
        System.out.print("11:");
        print(dll_populated.get(11));
        System.out.print("12:");
        print(dll_populated.get(12));
        System.out.print("13:");
        print(dll_populated.get(13));
        System.out.print("14:");
        print(dll_populated.get(14));
        System.out.print("15:");
        print(dll_populated.get(15));
    }

    public static void testCreateAndSize() {
        DoublyLinkedList<String> dll = new DoublyLinkedList<String>();
        assert dll.size() == 0 : "assertion failed A1";
    }

    public static void testGet() {
        DoublyLinkedList<Integer> dll = createPopulatedList();
        assert dll.get(0) == 0: "assertion failed #1";
        assert dll.get(1) == 1: "assertion failed #2";
        assert dll.get(2) == 2: "assertion failed #3";
        assert dll.get(8) == 8: "assertion failed #4";
        assert dll.get(9) == 9: "assertion failed #5";
    }

    public static void testInsertion() {
        // You should implement many of your own tests!
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();
        dll.append(10);
        assert dll.size() == 1 : "assertion failed B1";
        Integer old = dll.set(0, 123);
        assert old == 10 : "assertion failed B2";
        assert dll.get(0) == 123 : "assertion failed B3";
        // You should do more stuff here!
    }

    public static void testGetLastGetFirst() {
        DoublyLinkedList<Integer> dll = createPopulatedList();
        assert(dll.getLast() == 9) : "assertion failed D1";
        assert(dll.getFirst() == 0) : "assertion failed D2";
    }

    public static void testRemove() {
        DoublyLinkedList<Integer> dll = createPopulatedList();
        dll.remove(9);

        //dll.remove(10);
        assert dll.getFirst() == 0: "assertion failed C0";
        assert dll.get(0) == 0: "assertion failed C1";
        assert dll.get(1) == 1: "assertion failed C2";
        assert dll.get(2) == 2: "assertion failed C3";
        assert dll.getLast() == 9: "assertion failed C4";
        assert dll.get(9) == 9: "assertion failed C5";
    }

    public static void hints() {
        System.out.println("Look at the Ed Lesson on testing!");
        System.out.println("Consider using randomness to do large scale testing!");
    }

    public static void testAdd() {
        DoublyLinkedList<Integer> dll_populated = createPopulatedList();
        dll_populated.add(0,99);
        dll_populated.add(1,88);
        dll_populated.add(3,77);
        dll_populated.add(8,66);
        dll_populated.add(9,55);
        dll_populated.add(5,44);

        //dll.remove(10);

        assert dll_populated.get(0) == 99: "assertion failed E3";
        assert dll_populated.get(3) == 77: "assertion failed E4";
        assert dll_populated.getLast() == 9: "assertion failed E5";
        assert dll_populated.get(12) == 6: "assertion failed E6";
        assert dll_populated.get(9) == 66: "assertion failed E6";
    }

    public static void testMisc() {
        DoublyLinkedList<Integer> dll = createPopulatedList();
        //dll.get(10); //testing error raising
    }

    public static void testSet(){
        DoublyLinkedList<Integer> dll = createPopulatedList();
        dll.set(0,99);
        dll.set(4,88);
        dll.set(9,77);
        //dll.set(102,66); // testing that this breaks

        assert dll.get(0) == 99: "assertion failed F1";
        assert dll.get(4) == 88: "assertion failed F2";
        assert dll.get(8) == 8: "assertion failed F3";
        assert dll.get(9) == 77: "assertion failed F4";
    }

    public static void testPrepend() {
        DoublyLinkedList<Integer> dll = createPopulatedList();
        dll.prepend(99);
        dll.prepend(88);
        dll.prepend(77);
        assert dll.get(0) == 77: "assertion failed G1";
        assert dll.get(1) == 88: "assertion failed G2";
        assert dll.get(2) == 99: "assertion failed G3";
        assert dll.get(3) == 0: "assertion failed G4";
    }

    public static void main(String[] args) {
        System.out.println("Testing DoublyLinkedList Class...");
        testGet();
        testAdd();
        testGetLastGetFirst();
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