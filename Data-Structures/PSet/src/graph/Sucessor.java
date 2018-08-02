package graph;

//Find the 'next' node (in order successor of a given node in a binary search tree -> each node has a link to its parent
public class Sucessor {
    public static void main(String[] args) {
        BTNode root = new BTNode(4);
        root.addLeft(new BTNode(2));
        root.addRight(new BTNode(5));
        root.left.addLeft(new BTNode(1));
        root.left.addRight(new BTNode(3));
        root.right.addRight(new BTNode(6));
        System.out.println("Successor to 1 is " + successor(root.left.left, root.left.left)); // 2
        System.out.println("Successor to 4 is " + successor(root, root)); // 5
        System.out.println("Successor to 6 is " + successor(root.right.right, root.right.right)); // null
        System.out.println("Successor to 3 is " + successor(root.left.right, root.left.right)); // 4
    }

    /**We are working with a BST, meaning the nodes are already ordered
     * Furthermore, the definition of the successor is the next node in the list (in order)
     * Approach number 1 -> We can just convert the tree into an array via Breadth First Search, find the node/value, and return the next value after it
     * If it does not have a successor, return null.
     *
     * O(N) space and O(N) time
     *
     * BCR should be at least log(N) because we have to look for the given node
     *
     * Another approach is to use the fact that it has a link to its parent to look for the successor. Have variable successor to track successor
     * check node -> if null no successor
     * check successor -> if greater than original return it
     * check successor.right -> if not null then get the smallest value from that right subtree - leftmost value
     * traverse up tree to parent node
     *
     * O(logN) time and O(logN) space from recursion stack
     *
     * Implemented 2nd approach
     */
    public static BTNode successor(BTNode succ, BTNode original) {
        if(succ == null || succ.value > original.value) return succ; //found successor
        if(succ.right != null && succ.right.value > original.value) return leftMostChild(succ.right);
        return successor(succ.parent, original);
    }

    public static BTNode leftMostChild(BTNode node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
}
