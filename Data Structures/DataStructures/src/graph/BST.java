package graph;

//Complete binary tree
public class BST {
	
	int val;
	BST left, right;
	
	public BST(int val) {
		this.val = val;
	}
	
	public static boolean search(BST bst, int val) {
		if(bst == null)
			return false;	//base case
		
		if(val == bst.val)
			return true;
		
		if(bst.val > val)
			return BST.search(bst.left, val);
		else
			return BST.search(bst.right, val);
	}
	
	public static BST add(BST bst, int val) {
		if(bst == null)
			bst = new BST(val);
		else if(val < bst.val)
			bst.left = BST.add(bst.left, val);
		else
			bst.right = BST.add(bst.right, val);
		return bst;
	}
	
	//in order traversal print tree - left, root, right
	public static void inOrder(BST root) {
		if(root == null)
			return;
		inOrder(root.left);
		System.out.println(root.val);
		inOrder(root.right);
	}
	
	//pre-order traversal print tree - root, left, right
	public static void preOrder(BST root) {
		if(root == null)
			return;
		System.out.println(root.val);
		preOrder(root.left);
		preOrder(root.right);
	}
	
	//post-order traversal print tree - left, right, root
	public static void postOrder(BST root) {
		if(root == null)
			return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.println(root.val);
	}
	
}
