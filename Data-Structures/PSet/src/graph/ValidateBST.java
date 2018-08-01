package graph;

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
        System.out.println(isBST(root));
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
}
