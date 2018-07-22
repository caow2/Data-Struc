package graph;
//Implementation of Trie data structure (Prefix tree)
//Assume upper case and lower case are distinct cases
public class Trie {
	protected TrieNode root;
	
	public Trie() {
		root = new TrieNode(null, false);
	}
	
	public void add(String s) {
		addHelper(root, s, 0);
	}
	
	private void addHelper(TrieNode node, String s, int index) {
		if(s == null || s.length() == 0 || index >= s.length()) {
			node.terminating = true;
			return; //done
		}
		Character key = s.charAt(index);
		if(! node.children.containsKey(key)) //add it to list of children
			node.addChild(new TrieNode(key, false));
		addHelper(node.children.get(key), s, index + 1);
	}
	
	public boolean hasPrefix(String prefix) {
		return hasPrefixHelper(root, prefix, 0);
	}
	
	private boolean hasPrefixHelper(TrieNode node, String prefix, int index) {
		if(prefix == null || prefix.length() == 0 || index >= prefix.length())
			return true;
		Character key = prefix.charAt(index);
		if((!node.children.containsKey(key))) //no child 
			return false;	//In cases of terminating nodes, they shouldn't have any children anyway - no valid keys to get
		return hasPrefixHelper(node.children.get(key), prefix, index + 1);
	}
}
