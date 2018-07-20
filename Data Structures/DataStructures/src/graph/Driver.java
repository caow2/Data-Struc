package graph;

public class Driver {
	public static void main(String[] args) {
		BST root = new BST(0);
		root = BST.add(root, 2);
		root = BST.add(root, -2);
		root = BST.add(root, 1);
		
		//BST.inOrder(root);
		//BST.preOrder(root);
		BST.postOrder(root);
	}
}
