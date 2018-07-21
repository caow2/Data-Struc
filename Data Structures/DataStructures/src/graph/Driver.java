package graph;

public class Driver {
	public static void main(String[] args) {
		BST tree = new BST(0);
		tree.root = tree.add(tree.root, 2);
		tree.root = tree.add(tree.root, -2);
		tree.root = tree.add(tree.root, 1);
		
		//BST.inOrder(root);
		//BST.preOrder(root);
		//BST.postOrder(tree.root);
		
		MinHeap heap = new MinHeap();
		heap.add(1);
		heap.add(3);
		heap.add(4);
		heap.add(5);
		heap.add(2);
		System.out.println(heap.removeMin()); //1
		System.out.println(heap.removeMin()); //2
		heap.add(6);
		System.out.println(heap.removeMin()); //3
		heap.add(1);
		System.out.println(heap.removeMin()); //1
		System.out.println(heap.removeMin()); //4
	}
}
