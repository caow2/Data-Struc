package bit;
/** Given an integer and can flip exactly one bit from 0 to 1. Find longest sequence of ones you can create */
public class FlipBitToWin {
    public static void main(String[] args) {
        int num = 1775; //11011101111
        int num2 = 0b11001110;
        int num3 = 0b10101010;
        System.out.println(flipBit(num));
        System.out.println(flipBit(num2));
        System.out.println(flipBit(num3));

        System.out.println(flipBitOnePass(num));
        System.out.println(flipBitOnePass(num2));
        System.out.println(flipBitOnePass(num3));
    }
    /** Brute force algo
     *  Check each bit to see if its a 0. If 0, flip it and check for longest number of consecutive ones.
     *  O(N + Z*N) where Z is number of zero
     *  Worst case -> all 0 -> N + N^2
     */
    public static int flipBit(int num) {
        int i = 1, longest = 0, curr = 0;
        while(i < num) {
            if((i & num) == 0) {
                int temp = i | num;
                curr = longestSequenceOfOne(temp);
                longest = Math.max(longest, curr);
            }
            i <<= 1;
        }
        return longest;
    }

    public static int longestSequenceOfOne(int num) {
        int curr = 0, longest = 0, i = 1;
        while(i < num) {
            if((i & num )> 0) {
                curr++;
                longest = Math.max(longest, curr);
            }
            else
                curr = 0;
            i <<= 1;
        }
        return longest;
    }

    /** One pass approach -> keep track of number of current number of ones, longest number of ones, the number of ones since the last zero.
     *  Whenever we see a zero, we 'flip it' by pretending its a one and setting current = ones since last zero, and reset the number of ones since last zero
     *  to 0 -> start tracking again for the next time we encounter a zero. It essentially tracks the number of ones between each zero so we don't have to backtrack to count it
     *  when we 'flip' a 0.
     *  O(N) time
     */
    public static int flipBitOnePass(int num) {
        int curr = 0, longest = 0, onesSinceLastZero = 0, i = 1;
        while( i < num) {
            if((i & num) > 0) {
                curr++;
                onesSinceLastZero++;
            }
            else {
                //found zero - take the ones since last zero and use it as the curr - pretend we flipped this zero
                curr = onesSinceLastZero + 1; //increment 1 to include the current zero that we flipped to be one
                onesSinceLastZero = 0;
            }
            longest = Math.max(curr,longest);
            i <<= 1;
        }
        return longest;
    }
}
