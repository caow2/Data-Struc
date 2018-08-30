package recursivedp;

import java.util.List;
import java.util.LinkedList;
/** Imagine a robot sitting on the upper left corner of a grid with r rows and c cols.
 *  The robot can only move in 2 directions, right and down, but certain cells are off limits such that the robot cannot step on them.
 *  Design an algo to find a path for the robot from top left to go to the bottom right.
 */
public class RobotInAGrid {

    static class GridCoordinate {
        int x, y;

        public GridCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {1, 1, 1},
                {1, 0, 1},
                {1, 0, 1}
        };
        System.out.println(findPathBF(grid));
        System.out.println(findPathDP(grid));
    }
    /** Assume each grid[r][c] has a value -> 0 means cannot step on the grid -> invalid path.
     *                                        1 means it is a valid step
     *
     *  Assume there is a valid path from top left to bottom right - even though this approach will account for no path.
     *
     *  Brute Force approach with recursion -> Go all the way down to the bottom of the grid until you encounter a bad step,
     *  then go all the way to the right until you encounter a bad step.
     *  If the current r,c is the bottom right corner -> we have found a path.
     *
     *  Store path in a LL so that it is easy to append in the first position when popping recursion stack to show the robot's path.
     *  Runtime is O(c ^ r) because at the r - 1 th row, we check c - 1 times in the worst case -> ~ c times
     *  Then at the r - 2 row, for each position we step down and check the r - 1 th row again.
     *
     *  For r - 2 th row, number of steps is c - 1 + c - 2 + c - 3 + c - 4 + ... + c - c ~  c * c - ( 1 + 2 + 3 + ... + c)
     *  Which roughly equates to c ^ 2 - (c ^ 2 / 2) ~ c ^ 2 / 2
     *
     *  For r - 3 th row, we check r - 2th row, and r - 1th row.
     *  Overall runtime is c + c ^ 2 + c ^ 3 + ... + c ^ r because there are r rows and we check r - 1, r - 2, ... etc.
     *
     *  Spacewise, the recursion stack will take up most of the space - O(r + c)
     *
     *  Note that when checking r - 2th row, we are doing duplicate work checking r - 1th row again for each [r - 2][c]. We can memoize this in another 2d grid.
     *  That should lead us to the BCR -> O(rc) which makes sense because we might have to check the whole grid for a valid path. At each step we only know if we can step down or
     *  step right -> we don't know anything beyond that step.
     *
     *  In this sense, a Greedy approach also doesn't make sense. We could go down a valid step and encounter a dead end.
     *  Furthermore, if both the down step and the right step are both valid - which do we choose?
     */

    public static List<GridCoordinate> findPathBF(int[][] grid) {
        LinkedList<GridCoordinate> list = new LinkedList<GridCoordinate>();
        findPathHelperBF(grid, 0, 0, list);
        return list;
    }

    public static boolean findPathHelperBF(int[][] grid, int r, int c, LinkedList<GridCoordinate> LL) {
        int rows = grid.length, cols = grid[0].length;
        if(r >= rows || c >= cols || grid[r][c] == 0) return false;    //r or c is out of bounds

        if(r == rows - 1 && c == cols - 1 ||                //reached destination
           findPathHelperBF(grid, r + 1, c, LL) ||       //valid path in down direction
           findPathHelperBF(grid, r, c + 1, LL)) {      //valid path to the right
            LL.addFirst(new GridCoordinate(r, c));
            return true;
        }

        return false; // no path to the right or bottom
    }

    /** Dynamic Programming approach with memoization - have a 2d grid that for each r, c, a -1 will show that the step and any paths from that step are bad.
     *  Anything other than -1 assumes the step has not been checked.
     *  In this approach, we check r - 1 th row and update the matrix.
     *  For the r - 2 row, for each position we don't have to check c - 1 or c - 2 ... positions again. We can just check the matrix to see if the step below us or to the right is bad.
     *  Checking the matrix is just O(1) and in the worst case, we check r * c positions in the original grid.
     *
     *  O(r*c) Time with O(r + c + r * c) space
     */
    public static List<GridCoordinate> findPathDP(int[][] grid) {
        LinkedList<GridCoordinate> list = new LinkedList<GridCoordinate>();
        int[][] matrix = new int[grid.length][grid[0].length];
        findPathDPHelper(grid, 0, 0, list, matrix);
        return list;
    }

    public static boolean findPathDPHelper(int[][] grid, int r, int c, LinkedList<GridCoordinate> LL, int[][] memo) {
        int rows = grid.length, cols = grid[0].length;
        if(r >= rows || c >= cols || grid[r][c] == 0 || memo[r][c] < 0)
            return false;

        if(r == rows - 1 && c == cols - 1 ||                //reached destination
                findPathDPHelper(grid, r + 1, c, LL, memo) ||       //valid path in down direction
                findPathDPHelper(grid, r, c + 1, LL, memo)) {      //valid path to the right
            LL.addFirst(new GridCoordinate(r, c));
            return true;
        }

        //Down and Right Paths found no valid paths -> this step must be invalid too
        memo[r][c] = -1; //if trying to check any paths from this step, no need to.
        return false;
    }
}
