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
    private int size;

    /**
     * capacity tracks the total number of slots (used or unused) in the data array
     */
    private int capacity;

    private int head;

    private int tail;

    /**
     * data stores the raw objects
     */
    private T[] data;

    private void checkBounds(int uncorrected_idx, boolean exclusive) {
        int upperBound = (exclusive) ? size-1: size;
        int lowerBound = 0;
        int corrected_index = getCircularIndex(uncorrected_idx);
        if (lowerBound > uncorrected_idx || uncorrected_idx > upperBound) {
            throw new IndexOutOfBoundsException("Index " + uncorrected_idx + 
            ", corrected to " + corrected_index + ", is out of bounds. We used upper bound of "
             + upperBound + " and lower bound of " + lowerBound + ". this was an exclusive = " + exclusive + " check.");
        }
    }

    /**
     * Constructs an empty Dynamic Array
     */
    public DynamicArray() {
        size = 0;
        capacity = 1;
        tail = 0;
        head = 0;
        data = (T[]) new Comparable[capacity];
    }

    private void decrementTale() {
        tail = tail-1;
        if (tail < 0) {
            tail = (capacity-1);
        }
    }

    private void incrementTale() {
        tail = tail+1;
        if (tail > (capacity-1)) {
            tail = 0;
        }
    }

    private void decrementHead() {
        head = -1;
        if (head < 0) {
            head = (capacity-1);
        }
    }

    private void incrementHead() {
        head = head+1;
        if (head > (capacity-1)) {
            head = 0;
        }
    }

    private int getCircularIndex(int idx) {
        int val = (tail+idx)%capacity;
        //System.out.println("tail: "+tail+" idx: "+idx+" capacity: "+capacity + " result: " + val);
        return val;
    }

    private void grow(){

        //intialise
        int new_capacity = capacity * 2;
        T[] new_data = (T[]) new Comparable[new_capacity];
        
        for(int i = 0; i < size; i++) {
            new_data[i] = get(i);
        }

        data = new_data;
        head = size-1;
        tail = 0;
        capacity = new_capacity;
    }

    private void shrink(){
        if (capacity == 1) {
            return;
        }
        capacity = capacity / 2;
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

    private void make_safe_for_extra_element () {
        if (isFull()) {
            grow();
        }
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

    private boolean handleAddToEmptyList(T element) {
        if (size == 0) {
            size++;
            data[head] = element;
            return true;
        }
        return false;
    }

    @Override
    public boolean append(T element) {
        if (handleAddToEmptyList(element)) {
            return true;
        }
        make_safe_for_extra_element();
        size++;
        incrementHead();
        System.out.println("khjasdhjk asdghjkhasgdcjkh");
        System.out.println(data[head]);
        System.out.println(element);
        data[head] = element;
        System.out.println(data[head]);
        return true;
    }

    /**
     * Add an element to the beginning of the array. Returns true if successful,
     * false otherwise.
     * Time complexity for full marks: O(1*)
     * Again, O(1*) means constant amortized time.
     */
    @Override
    public boolean prepend(T element) {
        if (handleAddToEmptyList(element)) {
            return true;
        }
        make_safe_for_extra_element();
        decrementTale();
        data[tail] = element;
        size++;
        return true;
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

    private boolean checkHandledAddCases(int ix, T element) {
        if (handleAddToEmptyList(element)) {
            return true;
        }
        if (getCircularIndex(ix) == head+1){
            return append(element);
        }
        if (getCircularIndex(ix) == tail-1){
            return prepend(element);
        }
        return false;
    }

    private boolean addSlow(int ix, T element) {
        checkBounds(ix, false);
        make_safe_for_extra_element();
        size++;
        incrementHead();
        T element_to_move;
        System.out.println(head);
        for(int i = ix; i < size; i++) {
            int circular_i = getCircularIndex(i);
            element_to_move = data[circular_i];
            data[circular_i] = element;
            element = element_to_move;
        }
        return true;
    }
    
    @Override
    public boolean add(int ix, T element) {
        if (checkHandledAddCases(ix, element)) {
            return true;
        }
        return addSlow(ix, element);
    }

    /**
     * Return the element at index ix.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T get(int ix) {
        checkBounds(ix, true);
        int crtdIx = getCircularIndex(ix);
        return data[crtdIx];
    }

    public T get_literal(int ix) {
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
        int crtdIx = getCircularIndex(ix);
        T old = data[crtdIx];
        data[crtdIx] = element;
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
        for(int i = ix; i < size-1; i++) {
            set(i,get(i+1));
        }
        decrementHead();
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
        remove(0);
        return true; //meh, we assume it works
    }

    @Override
    public void clear() {
        size = 0;
        capacity = 1;
        tail = 0;
        head = 0;
        data = (T[]) new Comparable[capacity];
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

    private <T extends Comparable<? super T>> T[] mergeSort(T[] array) {
        
    }

    private <T extends Comparable<? super T>> T[] merge(T[] array1, T[] array2) {

    }
    
    public void sort() {

    }
}
