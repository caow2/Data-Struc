package graph;

import java.util.HashMap;

public class Graph {
    HashMap<Integer, GraphNode> nodes = new HashMap<Integer, GraphNode>();

    public Graph() {

    }

    public boolean add(int i) {
        GraphNode node = new GraphNode(i);
        return add(node);
    }

    public boolean add(GraphNode node) {
        Integer val = node.value;
        if (nodes.containsKey(val))
            return false; //already has that node
        nodes.put(val, node);
        return true;
    }

    public GraphNode get(Integer i) {
        return nodes.get(i);
    }

    //Connect for undirected graph A<->B
    public void UDConnect(int a, int b) {
        DConnect(a, b);
        DConnect(b, a);
    }

    //Connect for directed graph A->B
    public void DConnect(int a, int b) {
        GraphNode aNode = connectHelper(a), bNode = connectHelper(b);
        aNode.addChild(bNode);
    }

    //Check if key exists - if not, create the node and put it in nodes
    private GraphNode connectHelper(int i) {
        GraphNode node = nodes.get(i);
        if (node == null) {
            node = new GraphNode(i);
            add(node);
        }
        return node;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GraphNode value : nodes.values()) {
            sb.append(value.toString() + "\n");
        }
        return sb.toString();
    }
}
