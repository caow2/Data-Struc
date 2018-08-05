package graph;

//BTree class - A BTree is just a root node and 2 children nodes
public class BTNode {
    int value;
    boolean visited;
    BTNode left, right;
    BTNode parent; //used for sucessor problem and FCA
    public BTNode(int value) {
        this.value = value;
    }

    public void addLeft(BTNode node) {
        left = node;
        left.parent = this;
    }

    public void addRight(BTNode node) {
        right = node;
        right.parent = this;
    }

    public void addLeft(int value) {
        BTNode node = new BTNode(value);
        this.addLeft(node);
    }

    public void addRight(int value) {
        BTNode node = new BTNode(value);
        this.addRight(node);
    }

    public String toString() {
        return "" + value;
    }
}
