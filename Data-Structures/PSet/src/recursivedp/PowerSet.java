package recursivedp;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/** Return all subsets of a set */
public class PowerSet {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5};
        List<LinkedList<Integer>> results = new ArrayList<LinkedList<Integer>>();
        subsets(results, new LinkedList<Integer>(), -1 , arr, true);
        System.out.println(results);
    }

    /** When we think of creating a subset, we can either include an element or not include an element
     *  The number of all the possible subsets are 2 ^ N where N is number of element since we have 2 choices for each element.
     *
     *  Think of it as a tree -> at each step we include an element and add it to our list of results, or we don't.
     *  This is a recursion tree of depth n + 1 -> because we include the empty set.
     *  Therefore, 2^(n + 1) - 1 total nodes in the tree and O(2^(n+1)).
     *
     *  The boolean add is used to denote which case of the tree -> to add the current integer or not to the subset.
     *
     *  [] -> add=true [1], add=false []
     *
     *  There is a special case for the empty set, denoted by index -1.
     */
    public static void subsets(List<LinkedList<Integer>> results, LinkedList<Integer> subset, int index, int[] arr, boolean add) {
        if(index == -1)
            results.add(new LinkedList<Integer>());
        else if(index < 0 || index >= arr.length)
            return;

        boolean valid = add && index != -1;
        if(valid) {
            subset.add(arr[index]);
            results.add(new LinkedList<>(subset));
        }
        subsets(results, subset, index + 1, arr, true);
        subsets(results, subset, index + 1, arr, false);
        if(valid)
            subset.removeLast();
    }


}
