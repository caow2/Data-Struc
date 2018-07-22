package graph;

public class Driver {
	public static void main(String[] args) {
		System.out.println("BST:");
		BST tree = new BST(0);
		tree.root = tree.add(tree.root, 2);
		tree.root = tree.add(tree.root, -2);
		tree.root = tree.add(tree.root, 1);
		
		System.out.println("In-order traversal: ");
		BST.inOrder(tree.root);
		System.out.println("Pre-order traversal: ");
		BST.preOrder(tree.root);
		System.out.println("Post-order traversal: ");
		BST.postOrder(tree.root);
		
		System.out.println("Minimum Heap:");
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
		
		System.out.println("Trie:");
		Trie trie = new Trie();
		trie.add("MANY");
		System.out.println(trie.hasPrefix("MA"));	//true
		System.out.println(trie.hasPrefix("MAN"));	//true
		System.out.println(trie.hasPrefix("MAX"));	//false
		System.out.println(trie.hasPrefix("XAM"));	//false
		System.out.println(trie.hasPrefix(""));		//true
		System.out.println(trie.hasPrefix("MANYA"));//false
		trie.add("MANYA");
		System.out.println(trie.hasPrefix("MANYA"));//true
		System.out.println(trie.hasPrefix("ASDWQ"));//false
		
		//Didn't completely implement terminating word checking - method is close to hasPrefix with an extra terminating check
		
	}
}
