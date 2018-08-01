package graph;

//Check if a BTree is balanced. A balanced tree is a tree s.t. heights of 2 subtrees at any node never differ more than 1
public class CheckBalanced {
    public static void main(String[] args) {
        BTNode node = new BTNode(1);
        node.left = new BTNode(2);
        node.right = new BTNode(3);
        node.right.right = new BTNode(4);
        //node.right.right.right = new BTNode(5);

        System.out.println(checkBalance(node));
    }

    /* One idea is that we can traverse thru the tree recursively -> at each step get the height of the left child and the right child
     * However, this isn't very efficient.
     * We have N nodes to traverse thru, and we're repeating alot of work computing the heights at each node.
     * Not exactly sure how this is N log N
     *
     * Another approach -> compute the height same time as computing the check for balanced tree by either doing something like error checking or a specific value
     * Essentially checking height with a special case - once we encounter it we end recursion and pass the error up
     * In this case it is O(N) -> only checking each node once, but when we find our special case we stop and pass it all the way up the recursion stack.
     * Space wise, it is O(H) -> the height of the tree; we never have more than H items on our recursion stack
     */
    public static int checkBalance(BTNode root) {
        if(root == null)
            return 0;
        int leftheight = checkBalance(root.left) + 1;
        if(leftheight == Integer.MIN_VALUE)
            return leftheight;  //unbalanced
        int rightheight = checkBalance(root.right) + 1;
        if(rightheight == Integer.MIN_VALUE)
            return rightheight; //unbalanced

        if(Math.abs(leftheight - rightheight) > 1)
            return Integer.MIN_VALUE;   //pass error up

        return Math.max(leftheight, rightheight); //return height of this subtree
    }
}
