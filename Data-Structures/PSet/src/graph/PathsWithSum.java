package graph;
import java.util.ArrayList;

/**Given Binary Tree from which a node contains an integer value (positive or negative), count number of paths that sum to a given value.
 * Path does not need to start or end at the root of the tree or leaves.
 */
public class PathsWithSum {
    public static void main(String[] args) {
        BTNode root = new BTNode(5);
        root.addLeft(6);
        root.addRight(1);
        root.left.addLeft(-2);
        root.left.addRight(2);
        root.left.right.addLeft(4);
        root.right.addLeft(-4);
        root.right.addRight(3);
        root.right.right.addRight(-3);

        sumPath(root, 4);
        System.out.println(count);
    }

    static int count = 0;
    public static void sumPath(BTNode root, int target) {
        int pathval = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        sumPathHelper(root, target, pathval, list);
    }

    public static void sumPathHelper(BTNode root, int target, int pathval, ArrayList<Integer> list) {
        if(root == null) return;
        int temp = pathval;
        if(root.value == target)
            count++;
        for(int i = 0; i < list.size(); i++) {
            if(temp + root.value == target) //accounts for only this value too because we subtract everything from the pathval up to this node -> pathval - pathval + this value == target
                count++;
            temp -= list.get(i);
        }
        list.add(root.value);
        pathval += root.value;
        sumPathHelper(root.left, target, pathval, list);
        sumPathHelper(root.right, target, pathval, list);
        list.remove(list.size() - 1); //the element we just added
        //don't need to decrement pathval because it's specific to all recursion steps in subtrees -> not previous nodes

    }

    /**For a path, think of it as an array -> 2 - 4 - 5 - 1 - 3 - 8 - 1 would represent the array
     * [2, 4, 5 , 1 , 3 , 8, 1]
     * sumpath array [ 2, 6, 11, 12, 15, 23, 24 ]
     * We want to find out the subsequences that add up to target. This just becomes a contiguous subarray problem.
     * If a valid subarray sequence exists at a index, then all we need to do is check if sumpath[i] - target exists in the array.
     * For example, we are looking for 9 and when we check index 4 = 15, we do 15 - 9 = 6. If there is a subsequence in there such that it equals 9 to get 15,
     * then 15 - 9 should exist in the array (which it does).
     * Likewise, for 15, 23, 24 - if there is a subsequence s.t. == 9, 24 - 9 should exist in the array because the sumpath at index 6 = 24 results from that subarray being added to some
     * value in sumpath. 24 - 9 = 15
     *
     * For fast lookup so that we don't have to research the array each time, we can use a hashtable.
     * For every node we process, we check to see if currentsum - target is in the map, and if it is, we
     * @param root
     * @param target
     */
    public static void sumPathOptimized(BTNode root, int target) {

    }
}
