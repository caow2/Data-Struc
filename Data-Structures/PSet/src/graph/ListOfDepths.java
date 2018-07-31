package graph;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Given Binary Tree, create a linked list all nodes at each depth.
public class ListOfDepths {
    public static void main(String[] args) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
        root.right.right.left = new BTNode(10);

        List<LinkedList<BTNode>> list = listOfDepths(root);
        for(LinkedList<BTNode> l : list) {
            for(BTNode node : l) {
                if(node != null)
                    System.out.print(node.value + " ");
                else
                    System.out.print("null" + " ");
            }
            System.out.println();
        }
    }

    //BTree class - A BTree is just a root node and 2 children nodes
    public static class BTNode {
        int value;
        boolean visited;
        BTNode left, right;

        public BTNode(int value) {
            this.value = value;
        }

        public String toString() {
            return "" + value;
        }
    }

    /* Since we have to access the nodes of the graph by the root and then its children in left to right fashion,
     * We can use a modified BFS approach.
     * Have a Queue to track the next element for BFS and add elems to the queue accordingly
     * Have to track the levels and the elems, as well as a list for each depth. # elem >= (2 ^ level) - 1 indicates time for a new list
     * Assume that we don't include nulls in the LinkedLists
     */
    public static List<LinkedList<BTNode>> listOfDepths(BTNode root) {
        List<LinkedList<BTNode>> result = new ArrayList<LinkedList<BTNode>>();
        Queue<BTNode> q = new LinkedList<BTNode>(); //track next elem in BFS queue
        int elem = 0, level = 1;
        q.add(root);
        LinkedList<BTNode> list = new LinkedList<BTNode>();

        while(! q.isEmpty()) {
            BTNode node = q.remove();
            elem++;
            list.add(node);
            if(elem >= Math.pow(2, level) - 1) {
                level++;
                result.add(list);
                list = new LinkedList<BTNode>();
            }

            if(node == null)
                continue;

            node.visited = true; //don't come back to this node

            if(node.left == null || ! node.left.visited)
                q.add(node.left);
            if(node.right == null || ! node.right.visited)
                q.add(node.right);
        }

        //the last valid row is added to the result because we consider the null children of the last valid row even after processing the last row
        return result;
    }

}
