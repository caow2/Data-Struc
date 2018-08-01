package graph;

//BTree class - A BTree is just a root node and 2 children nodes
public class BTNode {
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
