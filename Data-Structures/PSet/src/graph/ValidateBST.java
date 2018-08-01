package graph;
import java.lang.reflect.Array;
import java.util.ArrayList;

//Check if a Binary Tree is a BST
//Assumes no dupes
public class ValidateBST {
    public static void main(String[] args) {
        BTNode root = new BTNode(3);
        root.left = new BTNode(1);
        root.right = new BTNode(5);
        root.left.right = new BTNode(2);
        //root.left.right = new BTNode(54); // false
        root.left.left = new BTNode(0);
        root.right.right = new BTNode(100);
        root.right.left = new BTNode(4);
        //root.right.left = new BTNode(2); //false
        //System.out.println(isBST(root));
        //System.out.println(isBST2(root));
        //System.out.println(isBST3(root, null));
        System.out.println(isBST4(root, null, null));

    }

    /* A binary search tree has the property that all of its left children are less than the root and all of the right children are greater than the root.
     * We can recursively go through the tree and check this.
     * Visit root - conduct checks. Visit left, visit right. Terminating cases are if the root is null nothing to check so it's true.
     * Enforce the property for all nodes. If we find one, then just return false.
     *
     * Runtime - O(N) because we should have to check through all the nodes. This is also BCR because there is no inherent order to a BT and there may be a node that does not have the properties of
     * a BST.
     *
     * Checking the left as a BST and checking the right as a BST are different cases because we are placing different constraints -> the left side must be < original root, etc.
     */
    public static boolean isBST(BTNode root) {
        if(root == null)
            return true;
        return checkLeft(root.left, root) && checkRight(root.right, root);
    }

    public static boolean checkLeft(BTNode root, BTNode superRoot) {
        if(root == null)
            return true;
        if(root.left != null && root.left.value > root.value)
            return false;
        if(root.right != null && (root.right.value < root.value || root.right.value > superRoot.value))
            return false;

        return checkLeft(root.left, superRoot) && checkLeft(root.right, superRoot);
    }

    public static boolean checkRight(BTNode root, BTNode superRoot) {
        if(root == null)
            return true;
        if(root.left != null && (root.left.value > root.value || root.left.value < superRoot.value))
            return false;
        if(root.right != null && root.right.value < root.value)
            return false;

        return checkRight(root.left, superRoot) && checkRight(root.right, superRoot);
    }

    /**
     * Cleaner approach - use depth first search and insert values into an array
     * Check if the current value is < last value
     * Assumes no duplicates
     * O(N) time to construct list and to loop thru it
     * O(N) space
     */
    public static boolean isBST2(BTNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        construct(root, list);

        for(int i = 1; i < list.size(); i++) {
            if(list.get(i) < list.get(i - 1))
                return false;
        }
        System.out.println(list);
        return true;
    }

    //BFS traversal and add to arraylist
    public static void construct(BTNode root, ArrayList<Integer> list) {
        if(root == null) return;
        construct(root.left, list);
        list.add(root.value);
        construct(root.right, list);
    }

    /**
     * Alternatively, we don't even need an array -> since we're always comparing to the last element, we can just run BFS and have a reference to it
     * O(N) time and O(1) space
     */
    public static boolean isBST3(BTNode root, BTNode previous) {
        if(root == null) return true;
        //run BFS - go all the way down to left first and then check root and then check right

        if(! isBST3(root.left, previous))
            return false;

        if(previous != null && root.value < previous.value)
            return false;   //only null until for processing the first node
        previous = root;

        if(! isBST3(root.right, previous))
            return false;

        return true;
    }

    /**
     * Cleaner approach of initial algorithm that was proposed
     * For every recursive check, we pass down a min and a max value to constrain the values within the tree
     * The left side of the tree is constrained by [null, top root value] and the other side of the tree is constrained by [top root value, null]
     * When checking the left binary tree, the rule has to be imposed too - all children to left of that is constrained by [null, root.left] and all children to right is constrained by
     * [root.left, null] -> this is okay because we already checked that [null,root] is valid; in actuality constrained by [root.left, root]
     */
    public static boolean isBST4(BTNode root, Integer min, Integer max) {
        if(root == null) return true; //nothing to compare

        if((min != null && root.value < min) || (max != null && root.value > max))
            return false;

        if(!(isBST4(root.left, min, root.value)) || !(isBST4(root.right, root.value, max)))
            return false;
        return true;
    }
}
