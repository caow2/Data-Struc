package graph;

import java.util.HashSet;

//Test class - also includes Depth first search and breadth first search implementations
public class Driver {
    public static void main(String[] args) {
        //testBST();
        //testMinHeap();
        //testTrie();
        //testGraph();
        //testAdjMatrix();
        //testDFSGraph();
        testDFSMatrix();
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
        System.out.println(trie.hasPrefix("MA"));    //true
        System.out.println(trie.hasPrefix("MAN"));    //true
        System.out.println(trie.hasPrefix("MAX"));    //false
        System.out.println(trie.hasPrefix("XAM"));    //false
        System.out.println(trie.hasPrefix(""));        //true
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

        System.out.println("Connect 2 -> 3:");    //3 is child of 2
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

    //Depth First Search - doesn't randomize starting node since in for each loop
    //See if node exists
    public static boolean DFSGraph(Graph graph, Integer value) {
        //Pick arbitary node -> for each loop. Loop thru all nodes of graph and the children of each node.
        //if encounter a node we've already seen - skip it because we examined all of its children already too
        //return false at end if still nothing
        HashSet<Integer> set = new HashSet<Integer>();
        for (Integer key : graph.nodes.keySet()) {
            if (key.equals(value))
                return true;

            if (set.add(key)) {    //if true set.add true, implies never seen node -> go thru children
                if (DFSGraphHelper(graph.nodes.get(value), value, set))
                    return true;
            }
        }
        return false;
    }

    //private helper
    private static boolean DFSGraphHelper(GraphNode node, Integer value, HashSet<Integer> set) {
        if(node == null)
            return false; //given a child that doesn't exist for this node
        if(node.value == value)
            return true;
        for(Integer key : node.children.keySet()) {
            if(key.equals(value))
                return true;

            if(set.add(value))
                if (DFSGraphHelper(node.children.get(key), value, set))
                    return true;
        }
        return false;
    }

    public static void testDFSGraph() {
        Graph g = new Graph();
        g.UDConnect(0,1);
        g.DConnect(0, 6);
        g.UDConnect(2,5);
        g.DConnect(2,3);
        g.UDConnect(0,3);

        System.out.println("Testing DFS for Graph:");
        System.out.println(g);

        System.out.println("Looking for 7: " + DFSGraph(g, 7));
        System.out.println("Looking for 5: " + DFSGraph(g, 5));
        System.out.println("Looking for 2: " + DFSGraph(g, 2));
        System.out.println("Looking for 10: " + DFSGraph(g, 10));
        System.out.println("Looking for 3: " + DFSGraph(g, 3));
        System.out.println("Looking for 6: " + DFSGraph(g, 6));
    }

    //DFS with adjacency matrix - same approach as with DFSGraph
    //This time, see if two nodes are connected
    public static boolean DFSMatrix(AdjacencyMatrix matrix, Integer a, Integer b) {
        HashSet<Integer> set = new HashSet<Integer>();
        if(a >= matrix.matrix.length || a < 0 || b < 0 || b >= matrix.matrix.length)
            return false; //path won't exist if one of them doesn't exist in matrix

        int[] row = matrix.matrix[a];
        for(int i = 0; i < row.length; i++) {
            if(row[i] > 0){
                if(i == b)
                    return true;
                if(set.add(i)) {
                    if(DFSMatrixHelper(matrix, i, b, set))
                        return true;
                }
            }
        }
        return false;
    }

    //look thru children
    private static boolean DFSMatrixHelper(AdjacencyMatrix matrix, Integer a, Integer b, HashSet<Integer> set) {
        //we're given an array representing a row and it's children
        if(a >= matrix.matrix.length || a < 0 || b < 0 || b >= matrix.matrix.length)
            return false;
        int[] row = matrix.matrix[a];
        for(int i = 0; i < row.length; i++) {
            if(row[i] > 0) {
                if(i == b)
                    return true;
                if(set.add(i)) {
                    if(DFSMatrixHelper(matrix,i , b, set))
                        return true;
                }
            }
        }
        return false;
    }

    public static void testDFSMatrix() {
        AdjacencyMatrix matrix = new AdjacencyMatrix(7);
        matrix.UDConnect(0,2);
        matrix.UDConnect(1, 4);
        matrix.UDConnect(2, 5);
        matrix.DConnect(3,4);
        matrix.DConnect(4,2);
        matrix.DConnect(4,6);

        System.out.println("Testing adjacency matrix:");
        System.out.println(matrix);

        System.out.println("Looking for path 1 to 5: " + DFSMatrix(matrix, 1,5)); // true 1->4->2->5
        System.out.println("Looking for path 3 to 5: " + DFSMatrix(matrix, 3,5)); // true 3->4->2->5
        System.out.println("Looking for path -1 to 5: " + DFSMatrix(matrix, -1,5)); // false -1 not in matrix
        System.out.println("Looking for path 7 to 5: " + DFSMatrix(matrix, 7,5));  //false
        System.out.println("Looking for path 2 to 6: " + DFSMatrix(matrix, 2,6));  //false
        System.out.println("Looking for path 3 to 6: " + DFSMatrix(matrix, 3,6));  //true
        System.out.println("Looking for path 0 to 4: " + DFSMatrix(matrix, 0,4));  //false
        System.out.println("Looking for path 7 to 0: "+ DFSMatrix(matrix, 7,0));  //false
    }
}

