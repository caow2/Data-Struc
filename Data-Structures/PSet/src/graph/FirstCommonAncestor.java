package graph;
import java.util.HashSet;

/**Find first common ancestor of 2 nodes in a binary tree.
 * Follow up -> avoid storing extra nodes in data structure
 */
public class FirstCommonAncestor {
    public static HashSet<Integer> set = new HashSet<Integer>();
    public static void main(String[] args) {
        BTNode root = new BTNode(1);
        root.addLeft(2);
        root.addRight(3);
        root.left.addLeft(4);
        root.right.addLeft(5);
        root.right.addRight(6);
        root.right.right.addLeft(7);

        /**Tree is
         *          1
         *      2       3
         *   4       5     6
         *               7
         */

        /*
        System.out.println(FCAImproved(root.left,root.right)); //1
        set = new HashSet<Integer>();
        System.out.println(FCA(root.left.left, root.left)); //2

        set = new HashSet<Integer>();
        System.out.println(FCAImproved(root.right.left, root.right.right.left)); //3
        set = new HashSet<Integer>();
        System.out.println(FCAImproved(root, root.right.right.left)); //1
        */

        System.out.println(FCADFS(root.left, root.right, root)); //1
        System.out.println(FCADFS(root.left.left, root.left, root)); //2
    }

    /**For 2 given nodes, let's assume they have a link to the parent, a list/map or children, and a value
     * If we get 2 nodes on the same level and want to find the FCA, we can compare whether the nodes are the same and if not, traverse up one level to the parent.
     * If we start from different nodes, we might have issues because we have no idea about any previous possible FCAs that we have seen -> If space is not a major concern,
     * cache the previously seen nodes in a set.
     *
     * We also assume that for two given nodes, there is a FCA (nodes are not null)
     * Algo:
     * 1. Init HashSet (Only init once)
     * 2. Compare the two nodes
     * 3. If same, return. Otherwise, check if in set. If in set, return the node because we've seen it before -> it is a common ancestor and is gauranteed to be the first common ancestor
     * because it is the first node they have in common when traversing up the tree. We would have returned the first one and stopped recursing otherwise.
     * 4. Recurse up to the parent and run this same method.
     * O(log N) time since we are traversing up the tree, or O(D) where D is the depth of the tree since this isn't necessarily a complete BST
     * O(log N) space -> we're not storing every node. Just the ones along the path from a node to the root (in worst case).
     *
     * Assume nodes in the tree are unique values.
     */

    public static BTNode FCA(BTNode a, BTNode b ){
        if(a == b || ! set.add(a.value))   //couldn't add it because it was already in set
            return a;
        if(! set.add(b.value))
            return b;
        return FCA((a.parent == null ? a : a.parent),       // in case we hit root node -> don't want to use nulls since root.parent == null
                    (b.parent == null ? b : b.parent));
    }

    /**If we are concerned about space, we could ditch the HashSet and have a boolean attribute in the Node class to indicate whether we've seen the node already.
     * Algo:
     * 1. Compare nodes. If same, return them.
     * 2. if a has already been visited before -> return a. Otherwise set a to visited.
     * 3. if b has already been visited before -> return b. Otherwise set b to visited.
     * We are basically keeping track of nodes that we've already seen, like in the previous approach.
     * 4. Recurse up and keep checking.
     *
     * **visited needs to be reset to false after each run.
     */
    public static BTNode FCAImproved(BTNode a, BTNode b) {
        if(a == b || a.visited)
            return a;
        if(b.visited)
            return b;
        a.visited = true;
        b.visited = true;
        return FCA((a.parent == null ? a : a.parent),
                    (b.parent == null ? b : b.parent));
    }

    /**Another approach could be to consider the idea of using DFS to find our FCA.
     * For the FCA, it has the property that it is the first node where a child can be found on the left subtree and the right subtree.
     * Is it possible there are multiple common ancestors that fulfill this property? Not in a binary tree because each node only has 1 parent.
     * Algo:
     * 1. Check if a and b are the same. If they are, we are done. If the root is null, return null to indicate that no child was found.
     * 2. Check the left subtree to see if a child is found there.
     * 3. Check the right subtree for the same reason. Since nodes are unique, it's impossible to find the same child in both the left and right subtree
     *    (Unless we allow that for a left child and a right child, the left child can be a child of the right child.)
     * 4. If left and right child are not null, this is the FCA - return root.
     * 5. If left child found, return left child to indicate it was found (anything except null is okay).
     * 6. If right child found, return right child.
     */
    public static BTNode FCADFS(BTNode a, BTNode b, BTNode root) {
        if(root == null)
            return null;
        if(a == b || a == root)
            return a;
        if(b == root)
            return b;

        BTNode left = FCADFS(a, b, root.left);
        BTNode right = FCADFS(a, b, root.right);

        if(left != null && right != null)
            return root;
        if(left != null)
            return left;
        return right; //takes care of case of both null and right != null
    }
}
