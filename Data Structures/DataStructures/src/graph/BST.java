package graph;

//Complete binary tree - children are also BST
public class BST {
	
	TreeNode root;
	
	public BST(int val) {
		root = new TreeNode(val);
	}
	
	public boolean search(TreeNode node, int val) {
		if(node == null)
			return false;	//base case
		
		if(val == node.val)
			return true;
		
		if(node.val > val)
			return search(node.left, val);
		else
			return search(node.right, val);
	}
	
	public TreeNode add(TreeNode node, int val) {
		if(node == null)
			node = new TreeNode(val);
		else if(val < node.val)
			node.left = add(node.left, val);
		else
			node.right = add(node.right, val);
		return node;
	}
	
	//in order traversal print tree - left, root, right
	public static void inOrder(TreeNode root) {
		if(root == null)
			return;
		inOrder(root.left);
		System.out.println(root.val);
		inOrder(root.right);
	}
	
	//pre-order traversal print tree - root, left, right
	public static void preOrder(TreeNode root) {
		if(root == null)
			return;
		System.out.println(root.val);
		preOrder(root.left);
		preOrder(root.right);
	}
	
	//post-order traversal print tree - left, right, root
	public static void postOrder(TreeNode root) {
		if(root == null)
			return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.println(root.val);
	}
	
}
