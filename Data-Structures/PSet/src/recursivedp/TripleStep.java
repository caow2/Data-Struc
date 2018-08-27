package recursivedp;

/** A child is running up the stairs with n steps. They can jump 1, 2, or 3 steps. How many total unique ways can they run up? */
public class TripleStep {

    public static void main(String[] args) {
        System.out.println(BFTripleStep(7)); // 44
        System.out.println(DPTripleStep(7)); // 44
        System.out.println(DPTripleStepOptimal(7)); // 44
    }

    /** We can think of this problem recursively as a Bottom-Up problem or a Top-Bottom problem.
     *  However, our subproblems remain the same here -> For a given step, how many ways can we reach it?
     *  If we can solve step(1), can we get step(2)? Will that help us?
     *
     *  Brute Force - Starting from step 0, we try every step size -> +1, +2, +3.
     *  Base case - n -> we stop and return +1 to show that we got here
     *              > n -> add 0 because we've exceeded this step -> don't count it
     *  Runtime for BF solution is exponential -> for each recursive function, we add 3 recursive function calls.
     *  A little less than O(3^N) because for +2, and +3, we don't do as many calls.
     *  O(N) space because the maximum stacks on the recursion stack is N -> 0 + 1 + 1 + 1 + ... = N
     */
    public static int BFTripleStep(int steps) {
        if(steps <= 2 && steps >= 0)
            return steps;
        return BFTripleStepHelper(steps, 0);
    }

    public static int BFTripleStepHelper(int step, int currentStep) {
        if(currentStep == step) return 1;
        if(currentStep > step) return 0;

        //Here we do recursive steps to count up to n - +1, +2, +3
        return BFTripleStepHelper(step, currentStep + 1) +
                BFTripleStepHelper(step, currentStep + 2) +
                BFTripleStepHelper(step, currentStep + 3);
    }

    /** In the BF Approach, we do alot of duplicated work, as in fibonacci. For example,
     *  We go all the way to the from x ... n many times. 1 - 2 - 3 - 4, 2 - 3 - 4, 1 - 2 - 4, 2 - 4, etc.
     *  Do we really need to know how many ways from 2 to 4 each time we get to 2? Or just how many ways to get to 2?
     *  Let's assume 4 is our N. You can get to 4 from 1, 2 and 3. 1, 2, and 3 can only get to 4 by adding 3, 2, and 1 respectively.
     *  The answer to number of ways should then be:
     *      -Number of ways to get to 1 + Number of ways to get to 2 + Number of ways to get to 3 because from there we can directly get to 4.
     *      or, memo[n - 3] + memo[n - 2] + memo[n - 1]
     *
     *  Bottom-Up Approach will start from 0 and go up to N. For each x between 0 ... N, we calculate x + 1, x + 2, and x + 3 and increment the respective x positions in a
     *  memo array.
     *  The values of the memo array will indicate how many ways are there to get to x.
     *  This is a one pass approach through the array from 0 ... N with 3 steps at each x. O(3N) ~ O(N) Time with O(N) space.
     *
     *  Is this the BCR? Do we need to check each path or is there an absolute formula/pattern we can use?
     *
     *  Our base cases are 0 to 3.
     */
    public static int DPTripleStep(int step) {
        if(step <= 2 && step >= 0) return step;
        if(step == 3) return 4;
        int[] memo = new int[step + 1]; //we want to track 0 ... step
        for(int i = 0; i <= 2; i++) {
            memo[i] = i; //init memo array
        }
        memo[3] = 4; //special case

        //Actual computing up to step
        for(int j = 4; j <= step; j++) {
            memo[j] = memo[j - 1] + memo[j - 2] + memo[j - 3];
        }

        return memo[step];
    }

    /** Like the fibonacci problem, we don't need whole array; just the values to represent the number of ways to get to
     *  n - 1, n - 2, and n - 3. We can just store them in variables as we loop from 3 ... n and update them correspondingly.
     *  O(N) runtime and O(1) space - improvement to the above algo spacewise.
     */
    public static int DPTripleStepOptimal(int step) {
        if( step <= 2 && step >= 0) return step;
        int a = 1, b = 2, c = 4;
        for(int i = 4; i <= step; i++) {
            int temp_ways = a + b + c; //number of ways to get to ith step.
            a = b;
            b = c;
            c = temp_ways;  // now c can be used to calculate i + 1
        }
        return c; // when the loop stops, c will be exactly the number of ways to get to i
    }

    /**Implement top bottom approach */
    
}
