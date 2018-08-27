package bit;

public class NextNumber {
    public static void main(String[] args) {
        nextNumber(6);
    }

    /** Given a positive integer print next smallest and largest number that has same number of 1s bits in binary
     *  -What does it mean to be next smallest and next largest? Is the next number just the next smallest?
     *
     *  If the answer to the above is yes, we can just get the next number, count the number of 1s in it,
     *  and then check until we find another another number that has same amount of 1s
     *
     *  Runtime is O(B * N), where B is number of bits to count for 1s, and N is number between nextSmall and nextLarge
     */
    public static void nextNumber(int num) {
        int nextSmall = num + 1, large = num + 1, ones = countBit(nextSmall);
        int largeBits = 0;
        boolean searching = true;
        while(searching) {
            largeBits = countBit(++large);
            if(largeBits == ones)
                searching = false;
        }

        System.out.println("Smallest: " + nextSmall + ". Bits: " + ones);
        System.out.println("Largest: " +  large + ". Bits: " + largeBits);
    }

    /** Count number of 1s in the given number */
    public static int countBit(int num) {
        int i = 1, counter = 0;
        while(i < num) {
            if((i & num) != 0)
                counter++;
            i <<= 1;
        }
        return counter;
    }

    /** Alternatively, we can try for a O(N) solution. If we have the next smallest, can't we just rearrange the ones to make the
     *  next largest number ?
     */
}
