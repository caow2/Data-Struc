package graph;

import java.util.LinkedList;
import java.util.HashSet;
public class CheckSubtree {
    public static void main(String[] args) {
        BTNode root = new BTNode(1);
        root.addLeft(2);
        root.addRight(3);
        root.left.addLeft(4);
        root.left.addRight(5);
        root.right.addLeft(6);
        root.right.addRight(7);

        BTNode t2 = root.right;

        System.out.println(contains(root, t2));
        System.out.println(containsBFS(root, t2));
        System.out.println(containsBFS(root, new BTNode(3))); //null
    }

    // assume bt node is just value, left, and right.
    public static boolean isSubtree(BTNode t1, BTNode t2) {
        return contains(t1, t2) == null;
    }

    /**Search for a given root node t2 in t1 by reference via DFS. If found, return t1. Otherwise, return null.
     *
     * @param t1
     * @param t2
     * @return
     */
    public static BTNode contains(BTNode t1, BTNode t2) {
        if(t1 == null) return null; //not found in t1
        if(t1 == t2) return t1;
        BTNode left = contains(t1.left, t2);
        BTNode right = contains(t1.right, t2);

        if(left != null)
            return left;
        return right; //if found return a node, if not on right and not on left, right should be null.
    }

    public static BTNode containsBFS(BTNode t1, BTNode t2) {
        if(t1 == null) return null;
        LinkedList<BTNode> queue = new LinkedList<BTNode>();
        HashSet<BTNode> set = new HashSet<BTNode>(); //to prevent causing cycles in the BTNode tree
        queue.add(t1);

        while(! queue.isEmpty()) {
            BTNode node = queue.removeFirst();
            if(node == t2)
                return node;
            if(set.add(node)) {
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
        }
        return null; // not found after processing all tree nodes
    }

}
