package graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Represents graph: For connected nodes A and B, A is a child of B and B is a child of A
// A <-> B
// If directed A->B, then A has child B
// Assume unique nodes with unique values
public class GraphNode {
    protected Integer value;
    protected Map<Integer, GraphNode> children;

    public GraphNode(int value) {
        this.value = value;
        children = new HashMap<Integer, GraphNode>();
    }

    public boolean addChild(GraphNode node) {
        Integer val = node.value;
        if (children.containsKey(val))
            return false; //already has that child
        children.put(val, node);
        return true;
    }

    public boolean removeChild(GraphNode node) {
        return children.remove(node.value, node);
    }

    public Integer getValue() {
        return value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(value + ": ");
        Iterator<Integer> it = children.keySet().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append(", ");
        }
        return sb.toString();
    }

    public Map<Integer, GraphNode> getChildren() {
        return children;
    }
}
