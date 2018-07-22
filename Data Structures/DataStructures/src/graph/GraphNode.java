package graph;

import java.util.HashMap;
import java.util.Map;

// Represents undirected graph: For connected nodes A and B, A is a child of B and B is a child of A
// A <-> B
// Assume unique nodes with unique values
public class GraphNode {
	Integer value;
	Map<Integer, GraphNode> children;
	
	public GraphNode() {
		children = new HashMap<Integer, GraphNode>();
	}
	
	public boolean addChild(GraphNode node) {
		Integer val = node.value;
		if(children.containsKey(val))
			return false; //already has that child
		children.put(val, node);
		return true;
	}
	
	public boolean removeChild(GraphNode node) {
		return children.remove(node.value, node);
	}
}
