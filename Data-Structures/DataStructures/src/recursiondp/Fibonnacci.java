package recursiondp;

public class Fibonnacci {

    public static void main(String[] args) {
        System.out.println(recursiveFibonacci(30));

        System.out.println(dpFibonacci(30));

        System.out.println(dpFibonnaciBottomTop(30));

        System.out.println(dpFibonacciOptimal(30));
    }

    /** Return nth fibonacci number through recursion. (Runtime is a little less than 2^N because of recursion tree and base cases). */
    public static int recursiveFibonacci(int n) {
        if(n == 1 || n == 0) return n;
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    public static int dpFibonacci(int n) {
        if(n == 1 || n == 0) return n;
        return dpHelper(n, new int[n + 1]); //want to be inclusive of 0...n -> int[n+1]
    }

    /** Memoization - Top to bottom approach. To solve f(4), we need to solve f(3) and f(2).
     *  If f(x) is 0 in the memo array, that means it has not been calculated yet -> calculate and store it with f(n-1) and f(n-2).
     *  Runtime is O(N) and space is O(N) because we go down recursion tree N times, and then all values on the other side of each respective f(x) has already been calculated.
     */
    public static int dpHelper(int n, int[] memo) {
        if(n == 0 || n == 1) return n;
        if(memo[n] == 0) //not calculated yet in the memo array
            memo[n] = dpHelper(n - 1, memo) + dpHelper(n - 2, memo);
        return memo[n];
    }

    /** Another approach from memoization with bottom -> top approach. Start with f(0) and f(1) and calculate f(2)...f(n)
     *  With a for loop, it is more obvious that the runtime is just O(N + 1) ~ O(N) -> One pass approach from 0 to N.
     */
    public static int dpFibonnaciBottomTop(int n) {
        if(n == 0 || n == 1) return n;
        int[] memo = new int[n + 1]; //same reason as before; want to cache 0 ... n
        memo[0] = 0;
        memo[1] = 1;

        for(int i = 2; i <= n; i++) {   //we want to compute n as well, so we stop at n + 1
            memo[i] = memo[i-1] + memo[i-2];    //guaranteed to have at least n - 2
        }
        return memo[n];
    }


    /** Do we really need the memo array? All we care about is f(n), f(n - 1), f(n - 2). Once we have calculated a f(n), we don't care for f(n - 2) anymore.
     *  It's not used to calculate f(n + 1).
     *  We can just store these values in a few variables to reduce the space complexity.
     */
    public static int dpFibonacciOptimal(int n) {
        if(n == 0 || n == 1) return n;
        int a = 0, b = 1; // a = n - 2, b = n - 1
        for(int i = 2; i <= n; i++) {
            int temp = a + b; // this is the current f(i). We need to update a -> f(n - 1) and b -> f(n) now to calculate f(n + 1)
            a = b;
            b = temp;
        }
        return b;
    }

}
