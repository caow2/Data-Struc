package bit;

public class BitManipulation {

    /* Return bit at position i of the number
     * True if 1, False if 0
     */
    public boolean getBit(int i, int num) {
        return ((1 << i) & num) != 0;
    }

    /*  Sets the ith bit to 1 in the number
     *
     */
    public int setBit(int i, int num) {
        return ((1 << i) | num);
    }

    /* Clears the bit at position i of num - set it to 0
     */
    public int clearBit(int i, int num) {
        int mask =  ~(1 << i); // 000100 -> 111011
        return mask & num; //if a bit is already 1, set it to 1 otherwise 0. For position i, set it to 0.
    }

    /* Clears bits from most significant bit to i (inclusive)
     */
    public int clearBiitsMSBthroughI(int i, int num) {
        int mask = (1 << i) - 1;
        return mask & num;
    }

    /* Clears bits from position i (inclusive) to 0
     */
    public int clearBitsIthrough0(int i, int num) {
        int mask = (-1 << (i + 1));
        return mask & num;
    }

    /* Update bit at position i. Set it to 1 if the boolean param is true, 0 otherwise.
     */
    public int updateBit(int i, int num, boolean one) {
        // Clearing bit i makes it easier to work with -> then we can just OR it.
        int value = one ? 1 : 0;
        int mask = value << i;
        return (num & mask) | (value << i);
    }
}
