package graph;

import java.util.HashSet;

//Test class - also includes Depth first search and breadth first search implementations
public class Driver {
	public static void main(String[] args) {
		//testBST();
		//testMinHeap();
		//testTrie();
		//testGraph();
		testAdjMatrix();
	}
	
	public static void testBST() {
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
	}
	
	public static void testMinHeap() {
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
	}
	
	public static void testTrie() {
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
	
	public static void testGraph() {
		System.out.println("Graph:");
		Graph graph = new Graph();
		graph.add(1);
		graph.add(2);
		System.out.println(graph);
		
		System.out.println("Connect 1 and 2:");
		graph.UDConnect(1, 2);
		System.out.println(graph);
		
		System.out.println("Connect 1 and 3:");
		graph.UDConnect(1, 3);
		System.out.println(graph);
		
		System.out.println("Connect 2 -> 3:");	//3 is child of 2
		graph.DConnect(2, 3);
		System.out.println(graph);
	}
	
	public static void testAdjMatrix() {
		System.out.println("5 node Adjacency Matrix: (0,1,2,3,4)");
		AdjacencyMatrix matrix = new AdjacencyMatrix(5);
		System.out.println(matrix);
		
		System.out.println("\nConnect 1 and 2:");
		matrix.UDConnect(1, 2);
		System.out.println(matrix);
		
		System.out.println("\nConnect 1 and 3:");
		matrix.UDConnect(1, 3);
		System.out.println(matrix);
		
		System.out.println("\nConnect 2 -> 3:");
		matrix.DConnect(2, 3);
		System.out.println(matrix);
	}
	
	//Depth First Search
	public static boolean DFSGraph(Graph graph) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(Integer key : graph.nodes.keySet()) {
			
		}
	}
	
	public static boolean DFSGraphHelper(GraphNode node, HashSet<Integer> set) {
		
	}
}

