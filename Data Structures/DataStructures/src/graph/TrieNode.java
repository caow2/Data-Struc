package graph;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Character value;
    Map<Character, TrieNode> children;
    boolean terminating;

    public TrieNode(Character value, boolean terminating) {
        children = new HashMap<Character, TrieNode>();
        this.value = value;
        this.terminating = terminating;
    }

    public boolean addChild(TrieNode node) {
        Character c = node.value;
        if (children.containsKey(c))
            return false; //already has this child
        children.put(c, node);
        return true;
    }
}
