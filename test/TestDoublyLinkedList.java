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

    public static void testCreateAndSize() {
        DoublyLinkedList<String> dll = new DoublyLinkedList<String>();
        assert dll.size() == 0 : "assertion failed A1";
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
        dll.remove(8);
        //dll.remove(10);
        assert dll.get(0) == 0: "assertion failed C1";
        assert dll.get(1) == 1: "assertion failed C2";
        assert dll.getLast() == 9: "assertion failed C3";
        assert dll.get(8) == 9: "assertion failed C4";
    }

    public static void hints() {
        System.out.println("Look at the Ed Lesson on testing!");
        System.out.println("Consider using randomness to do large scale testing!");
    }

    public static void testAdd() {
        DoublyLinkedList<Integer> dll_populated = createPopulatedList();
        dll_populated.add(8,99);
        dll_populated.add(0,88);
        dll_populated.add(11,77);
        //dll.remove(10);
        assert dll_populated.get(0) == 88: "assertion failed E1";
        assert dll_populated.get(1) == 0: "assertion failed E2";
        assert dll_populated.get(9) == 99: "assertion failed E3";
        assert dll_populated.get(10) == 8: "assertion failed E4";
        assert dll_populated.getLast() == 77: "assertion failed E5";
        assert dll_populated.get(12) == 77: "assertion failed E6"; //fails here, probs tail not updated
    }

    public static void testMisc() {
        DoublyLinkedList<Integer> dll = createPopulatedList();
        print(dll.get(9));
        //dll.get(10); //testing error raising
    }

    public static void main(String[] args) {
        System.out.println("Testing DoublyLinkedList Class...");
        testGetLastGetFirst();
        testCreateAndSize();
        testInsertion();
        testRemove();
        testAdd();
        testMisc();
        System.out.println("Success!");
        assert 1==2 : "Assertions are working!";
    }
}
