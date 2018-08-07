package graph;
import java.util.LinkedList;
import java.util.ArrayList;
/**A BST is created by traversing thru an array from left to right and inserting each elem.
 * Given a BST with distinct elems, print all possible arrays that could have led to this tree.
 */
public class BSTSequence {

    public static void main(String[] args) {

    }

    /**Idea is to recurse down to each child node, find all possible subsequences and append to a list,
     * and then for every root, append the root to the start of the subsequences.
     */
    public static ArrayList<LinkedList<Integer>> BSTSequence(BTNode root) {
        ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>();

        if(root == null) {
            list.add(new LinkedList<Integer>());
            return list;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(root.value);

        ArrayList<LinkedList<Integer>> left = BSTSequence(root.left), right = BSTSequence(root.right);
        for(LinkedList<Integer> l : left) {
            for(LinkedList<Integer> r : right) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weave(l, r , weaved, prefix);
            }
        }
        return list;
    }

    /**Weaves together the lists in all possible ways while maintaining the specific weave order
     */
    public static void weave(LinkedList<Integer> left, LinkedList<Integer> right, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        //If one list is empty -> add all of other list to the prefix and then to results
        if(left.size() == 0 || right.size() == 0) {
            LinkedList<Integer> result = new LinkedList<>(prefix);
            result.addAll(left);
            result.addAll(right);   //doesn't matter which we add - one of them is empty anyway
            results.add(result);
            return;
        }

        /**Append one side to prefix until it is empty, add the resulting list to results, remove element added to prefix, and weave the other side.
         *
         */
        int leftHead = left.removeFirst();
        prefix.addLast(leftHead);
        weave(left, right, results, prefix);
        prefix.removeLast();
        left.addFirst(leftHead);

        int rightHead = right.removeFirst();
        prefix.addLast(rightHead);
        weave(left, right, results, prefix);
        prefix.removeLast();
        right.addFirst(rightHead);
    }


}
