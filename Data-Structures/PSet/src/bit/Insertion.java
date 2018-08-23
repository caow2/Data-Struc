package bit;

/** Given 2 32-bit numbers M and N, and 2 bit positions i and j. Insert M into N such that M starts at position j and ends at i.
 *  Assume that j through i will have enough spots to fit M.
 *  Additional assumption - assume that j and i will have exact spots to fit M
 */
public class Insertion {
    public static void main(String[] args) {
        int n = 0x400;
        int m = 0x13;
        System.out.println(insert(n,m,6,2));
    }

    /** Easiest way would be to clear the bits from j to i in N,
     *  shift M i positions because that will be where it ends
     *  OR the new N and the new M
     */
    public static int insert(int M, int N, int i, int j) {
        int n = clearBits(i, j, N);
        int m = M << i;
        return n | m;
    }

    /** Clear bits from i to j of of num. */
    public static int clearBits(int i, int j, int num) {
        //make 1 part first -> 111110000 and then make the trailing 1s -> 11111000011...
        int frontmask = -1 << (j + 1);
        int backmask = (1 << i) - 1;
        return (frontmask | backmask) & num;
    }
}
