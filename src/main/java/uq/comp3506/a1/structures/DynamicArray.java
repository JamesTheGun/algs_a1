// @edu:student-assignment

package uq.comp3506.a1.structures;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 *
 * NOTE: You should go and carefully read the documentation provided in the
 * ListInterface.java file - this explains some of the required functionality.
 */
public class DynamicArray<T extends Comparable<T>> implements ListInterface<T> {

    /**
     * size tracks the total number of slots being used in the data array
     */
    private int size = 0;

    /**
     * capacity tracks the total number of slots (used or unused) in the data array
     */
    private int capacity = 0;

    /**
     * data stores the raw objects
     */
    private T[] data;

    private void checkBounds(int idx, boolean exclusive) {
        int upperBound = (exclusive) ? size : size + 1;
        if (idx < 0 || idx >= upperBound) {
            throw new IndexOutOfBoundsException("Index " + idx + " is out of bounds.");
        }
    }

    /**
     * Constructs an empty Dynamic Array
     */
    public DynamicArray() {
        data = (T[]) new Comparable[capacity];
    }

    private void grow(){
        capacity++;
        data = (T[]) new Comparable[capacity];
    }

    private void shrink(){
        capacity--;
        data = (T[]) new Comparable[capacity];
    }

    // See ListInterface
    @Override
    public int size() {
        return size;
    }

    // See ListInterface
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Has the size reached the current capacity?
     * Return true if so, false otherwise.
     * This is merely a convenience function for you. We will not be
     * testing it explicitly.
     */
    public boolean isFull() {
        if (size == capacity) {
            return true;
        }
        return false;
    }

    /**
     * Get current capacity.
     * Again, this is merely a convenience function for you. We will not
     * be testing it explicitly.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Add an element to the end of the array. Returns true if successful,
     * false otherwise.
     * Time complexity for full marks: O(1*)
     * That is, O(1) *amortized*.
     */
    @Override
    public boolean append(T element) {
        if (!isFull()) {
            data[size++] = element;
            return true;
        }
        return false;
    }

    /**
     * Add an element to the beginning of the array. Returns true if successful,
     * false otherwise.
     * Time complexity for full marks: O(1*)
     * Again, O(1*) means constant amortized time.
     */
    @Override
    public boolean prepend(T element) {
        return add(0,element);
    }

    /**
     * Add element to index ix.
     * Note: This does not overwrite the element at index ix - that is what
     * the set() method is for, see below. Instead, this function is similar
     * to append or prepend, but it adds the element at a desired index.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Acceptable bounds are [0, size] where 0 will be prepend, size will
     * be append, and anything in between will need to shuffle elements around.
     * Time complexity for full marks: O(N)
     */
    @Override
    public boolean add(int ix, T element) {
        checkBounds(ix, false);
        if (!isFull()) {
            size++;
            T element_to_move;
            for(int i = ix; i < size; i++) {
                element_to_move = data[i];
                data[i] = element;
                element = element_to_move;
            }
            return true;
        }
        return false;
    }

    /**
     * Return the element at index ix.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T get(int ix) {
        checkBounds(ix, false);
        return data[ix];
    }

    /**
     * Overwrite the "old" value at ix with element, and return the old value.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T set(int ix, T element) {
        checkBounds(ix, true);
        T old = data[ix];
        data[ix] = element;
        return old;
    }

    /**
     * Remove and return the value at index ix
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(N)
     */
    @Override
    public T remove(int ix) {
        checkBounds(ix, true);
        T element = get(ix);
        for(int i = ix; i < size; i++) {
            data[i] = data[i+1];
        }
        size--;
        return element;
    }

    /**
     * Find and remove the first value in the array that equals t (the one
     * with the smallest index).
     * Return true if successful, false otherwise.
     * Time complexity for full marks: O(N)
     */
    @Override
    public boolean removeFirst(T t) {
        for(int i = 0; i < size; i++) {
            if (data[i] == t) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
    for (int i = 0; i < size; i++) {
        data[i] = null;
    }
    size = 0;
    }

    /**
     * Sort all of the elements inside the array.
     *
     * Time complexity for full marks: O(NlogN).
     * That is, we expect you to implement a sorting algorithm that runs in
     * "n log n" time. This may be in expectation, or guaranteed worst case.
     *
     * A note on comparisons:
     *
     * You may assume that any type stored inside the DynamicArray already
     * implements Comparable<T> which means you can just use compareTo()
     * in order to sort elements.
     *
     * We will assume sorting in ascending, so you will want to do something
     * like: if (data[i].compareTo(data[j]) < 0) { // data[i] < data[j] }
     */
    public void sort() {

    }
}
