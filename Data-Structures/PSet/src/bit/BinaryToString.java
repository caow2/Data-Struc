package bit;

/** Given a real number between 0 and 1 that is passed as a double, print
 *  the binary representation. If number can't be represented accurately in at most 32 chars, print error.
 */
public class BinaryToString {
    public static void main(String[] args) {
        double d = .75;
        System.out.println(BinToString(d));
    }

    /** Initial implementation -> check if less than 1/2^32. If it is, we are already done.
     *  Otherwise, loop thru until 1/2^32 and add it to a new variable double. If dx + 1/2^x <= d then we add it and append 1 in that position. Otherwise append 0.
     *  After the loop, if it is still not 2 ^ 32
     * @param d
     * @return
     */
    public static String BinToString(double d) {
        if(d <= 0 || d >= 1) return "ERROR"; //not a decimal
        StringBuilder result = new StringBuilder();
        result.append(".");
        double x = .5;
        while(d > 0) {
            if(result.length() > 32)
                return "ERROR";
            if(d >= x) {
                d -= x;
                result.append("1");
            }
            else
                result.append("0");
            x /= 2;
        }

        return result.toString();
    }
}
