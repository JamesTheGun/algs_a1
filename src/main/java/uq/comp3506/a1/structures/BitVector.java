// @edu:student-assignment

package uq.comp3506.a1.structures;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */
public class BitVector {

    private int dataIndx;
    private long val;
    private long bitMask;

    // The number of bits per integer stored
    public static final int BitsPerElement = 64;

    /**
     * The number of "active" bits that can be stored in this bitvector
     */
    private final long size;

    /**
     * The total number of bits allocated in the data array
     */
    private final long capacity;

    private final long numberOfLongs;

    /**
     * We use 'long' instead of 'int' to store elements because it can fit
     * 64 bits instead of 32
     */
    private long[] data;

    /**
     * Constructs a bitvector, pre-allocating enough memory to store `size` bits
     */
    public BitVector(long size) {
        this.size = size;
        int numberOfLongsNeeded = ((int)(size%64) != 0) ? (int)(size/64) + 1 : (int)(size/64);
        capacity = numberOfLongsNeeded*64;
        data = new long[numberOfLongsNeeded];
        numberOfLongs = numberOfLongsNeeded;
    }

    /**
     * Returns the total number of bits that can be used
     */
    public long size() {
        return size;
    }

    /**
     * Returns the total number of bits allocated in the data array
     */
    public long capacity() {
        return capacity;
    }

    private void checkBounds(long idx, boolean exclusive) {
        long upperBound = (exclusive) ? size : size + 1;
        if (idx < 0 || idx >= upperBound) {
            throw new IndexOutOfBoundsException("Index " + idx + " is out of bounds.");
        }
    }

    private void setVarsForAccessingBit(long ix) {
        checkBounds(ix,true);
        dataIndx = (int)(ix/64);
        val = data[dataIndx];
        bitMask = 1L << (ix - dataIndx*64);
    }
    

    /**
     * Returns the value of the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */

    public boolean get(long ix) {
        setVarsForAccessingBit(ix);
        boolean bitIX = (val & bitMask) != 0;
        return bitIX;
    }

    /**
     * Set the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */
    public void set(long ix) {
        setVarsForAccessingBit(ix);
        val |= bitMask;
        data[dataIndx] = val;
    }

    /**
     * Unset the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */
    public void unset(long ix) {
        setVarsForAccessingBit(ix);
        val = ~bitMask;
        data[dataIndx] = val;
    }

    /**
     * Convert the BitVector to its complement
     * That means, all 1's become 0's and all 0's become 1's
     */
    public void complement() {
        long thisLong;
        long allOnesMask = ~0l;
        for (int i = 0; i < numberOfLongs; i++) {
            thisLong = data[i];
            thisLong ^= allOnesMask;
            data[i] = thisLong;
            }
        }

    /**
     * Shift the bits `dist` positions
     * If dist is positive, this is a left shift, assuming the least significant
     * bit is the rightmost bit. So, consider you have a 4 element bitvector:
     * Indexes:  3 2 1 0
     * Elements: 1 1 0 1
     * Doing a shift(2) would yield:
     * Indexes:  3 2 1 0
     * Elements: 0 1 0 0
     *             ^--- This bit was previously at index 0
     *           ^----- This bit was previously at index 1
     *
     * Don't forget that you must also handle negative values of dist, and
     * these will invoke a right shift.
     *
     * The bits that "fall off" are always replaced with 0's.
     */
    public void shift(long dist) {
        int shiftAmount = (int)((dist < 0 ? -dist : dist) & 63); //clever mod 64
        if (dist > 0) { //left sift
            long previousCaptureCarryOutMask = 0L;
            for (int i = 0; i < numberOfLongs; i++){
                long thisLong = data[i];

                long captureCarryOutMask = thisLong >>> (64 - shiftAmount);
                thisLong <<= shiftAmount;
                thisLong |= previousCaptureCarryOutMask;

                data[i] = thisLong;
                previousCaptureCarryOutMask = captureCarryOutMask;
            }
        }else{ //right sift
            long previousCaptureCarryOutMask = 0L;
            for (int i = (int) numberOfLongs - 1; i >= 0; i--){
                long thisLong = data[i];
                long captureCarryOutMask = thisLong << (64 - shiftAmount);

                thisLong >>>= shiftAmount;
                thisLong |= previousCaptureCarryOutMask;

                data[i] = thisLong;
                previousCaptureCarryOutMask = captureCarryOutMask;
            }
        }
    }
    /**
     * Rotate the bits `dist` positions
     * If dist is positive, this is a left rotation, assuming the least significant
     * bit is the rightmost bit. So, consider you have a 5 element bitvector:
     * Indexes:  4 3 2 1 0
     * Elements: 1 1 1 0 1
     * Doing a rotate(2) would yield:
     * Indexes:  4 3 2 1 0
     * Elements: 1 0 1 1 1
     *                 ^This bit was previously at index 4
     *             ^--- This bit was previously at index 1
     *           ^----- This bit was previously at index 2
     * As you can see, it operates the same as the shift, but the bits that
     * are moved "off the end" of the vector wrap back around to the beginning.
     *
     * Don't forget that you must also handle negative values of dist, and
     * these will invoke a right shift.
     */

public void rotate(long dist) {
    if (size <= 1) return;

    // Use magnitude for the branch you’re taking
    int rotationAmount = (int) (dist >= 0 ? Math.floorMod(dist, size)
                             : Math.floorMod(-dist, size));
    if (rotationAmount == 0) return;

    BitVector carryOut = new BitVector(size);

    if (dist >= 0) {
        for (long i = size - rotationAmount; i < size; i++) {
            if (get(i)) carryOut.set(i);
        }
        shift(rotationAmount);
        for (int i = 0; i < rotationAmount; i++) {
            if (carryOut.get(size - rotationAmount + i)) set(i);
        }
    } else {
        for (int i = 0; i < rotationAmount; i++) {
            if (get(i)) carryOut.set(i);
        }
        shift(-rotationAmount);

        for (int i = 0; i < rotationAmount; i++) {
            if (carryOut.get(i)) set((int)(size - rotationAmount + i));
        }

    }
}


    /**
     * COMP7505 only (COMP3506 may do this for fun)
     * Returns the number of bits that are set to 1 across the entire bitvector
     */
    public long popcount() {

        return -1;
    }

    /**
     * COMP7505 only (COMP3506 may do this for fun)
     * Returns the length of the longest run of bits that are set (1) across
     * the entire bitvector
     */
    public long runcount() {

        return -1;
    }

}
