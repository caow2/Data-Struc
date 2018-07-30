package graph;

// Given a sorted (increasing order) array with unique integer elems, write algo to create binary search tree with minimal height
public class MinimalTree {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        BSTree tree = constructTree(arr, 0, arr.length - 1);
        BSTree.inOrderTraversal(tree);
        System.out.println("Max depth: " + BSTree.getMaxDepth(tree));
    }

    //BSTree Node
    public static class BSTree {
        int val;
        BSTree left, right;

        public BSTree(int val) {
            this.val = val;
        }

        public static void inOrderTraversal(BSTree tree) {
            if (tree == null) return;
            inOrderTraversal(tree.left);
            System.out.println(tree.val);
            inOrderTraversal(tree.right);
        }

        public static int getMaxDepth(BSTree tree) {
            if(tree == null) return 0;
            int leftMax = 1 + getMaxDepth(tree.left);
            int rightMax = 1 + getMaxDepth(tree.right);
            return (leftMax > rightMax ? leftMax : rightMax);
        }
    }

    //Similar to Binary Search in that we need to consider all the midpoints at each step because that is the node we want to build
    //Runtime should be O(N) -> consider every value in the int arr. This is also the best conceivable runtime b/c we can't miss any values.
    //Space is O(N) for the BSTree and the recursion stack is O(logN)
    public static BSTree constructTree(int[] arr, int start, int end) {
        if(start > end) return null; //nothing to construct - reached an invalid index
        int midpt = (start + end )/2;
        BSTree t = new BSTree(arr[midpt]);
        t.left = constructTree(arr, start, midpt - 1);
        t.right = constructTree(arr, midpt + 1, end);
        return t;
    }



}
