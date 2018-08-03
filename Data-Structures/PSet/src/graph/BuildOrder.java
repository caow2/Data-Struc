package graph;

import java.util.*;

/**
 * Given a list of projects and given a list of dependencies (a list of pairs of projects where 2nd project is dependent on first)
 * All of a projects dependencies must be built before the project is.
 * Find a build order that will allow the projects to be built.
 * If no valid build order, return an error
 */
public class BuildOrder {
    public static void main(String[] args) {
        List<String> projects = new ArrayList<String>();
        for(int i = 'a'; i <= 'f'; i++) {
            projects.add("" + (char) i);
        }
        System.out.println(projects);

        List<Dependency> dep = new LinkedList<Dependency>();
        dep.add(new Dependency("a", "d"));
        dep.add(new Dependency("f", "b"));
        dep.add(new Dependency("b", "d"));
        dep.add(new Dependency("f", "a"));
        dep.add(new Dependency("d", "c"));
        System.out.println(dep);

        try {
            System.out.println(buildOrder(projects, dep));     //f, e, a, b, d, c or e, f, a, b, d, c
        }
        catch(Exception e) {
            System.out.println(e);
        }

        dep.add(new Dependency("b", "e"));      //e is dependent on f now so f should be first
        try {
            System.out.println(buildOrder(projects, dep));     //f, a, b, d, e, c or something similar
        }
        catch(Exception e) {
            System.out.println(e);
        }

        dep.add(new Dependency("a", "f"));      //should break now that there is a cycle
        try {
            System.out.println(buildOrder(projects, dep));     //break
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * projects: a,b,c,d,e,f
     * dependencies: (a,d), (f,b), (b,d), (f,a), (d,c)
     *
     * If we represent as a graph where the root node is a stub, we get
     *          stub
     *      f          e
     *  a       b
     *      d       c
     *
     * where d is a child of both a and b. The build order would be f, e, a, b, d, c, which strong resembles a Breadth First Search approach.
     *
     * In short, we have to determine whether there is a cycle in the tree -> if there is, there cannot be a proper build order
     * Also, if a depends on b and b depends on a, there cannot be a proper build order.
     *
     * Then we construct the tree to perform BFS on.
     * We can do this by finding the nodes that don't depend on anything and adding them as children to our root node.
     *
     * BFS is O(N) and the tree will take up O(N) space where N is number of nodes.
     * Checking if there's a cycle will also be O(C) where C is the number of dependencies/vertices
     *
     * Runtime is O(N + C)
     *
     * Find if cycle first, then find the projects with no dependencies -> start BFS from there
     */
    public static String buildOrder(List<String> projects, List<Dependency> dependency) throws Exception {
        StringBuilder sb = new StringBuilder();
        HashMap<String, GraphNode> map = createList(projects, dependency);  //O(N + C)

        Set<String> set = children(map);    //O(N + C)
        if(set.size() == map.size())
            throw new Exception("No Build Order");

        LinkedList<GraphNode> queue = new LinkedList<GraphNode>(); //at least one project without dependencies -> find it

        for(String s : projects) {
            if(! set.contains(s))
                queue.add(map.get(s));
        }

        set = new HashSet<String>(); //no longer need original keys -> use this to track the nodes we've already seen

        //O(N)
        while(! queue.isEmpty()) {
            GraphNode node = queue.remove();
            for(String s : node.children.keySet()) {
                if(! set.contains(s)) {
                    set.add(s);
                    queue.add(map.get(s));
                }
            }

            sb.append(node.value);
            if(! queue.isEmpty())
                sb.append(", ");
        }

        return sb.toString();
    }

    public static HashMap<String, GraphNode> createList(List<String> projects, List<Dependency> dependency) {
        HashMap<String, GraphNode> nodes = new HashMap<String, GraphNode>();

        for(String project : projects) {
            nodes.put(project, new GraphNode(project));
        }

        for(Dependency d : dependency) {
            GraphNode node = nodes.get(d.root);
            node.children.put(d.dependent, nodes.get(d.dependent));
        }
        return nodes;
    }

    //loop thru all nodes in graph and add their children to a set
    //if a graph has a cycle, all nodes must be a children of another node -> compare size of set
    //Runtime is O(V) -> number of edges in graph linking from parent to child
    // O(N) space - max is N nodes in set
    public static Set<String> children(HashMap<String, GraphNode> map) {
        Set<String> set = new HashSet<String>();
        for(String key : map.keySet()) {
            GraphNode node = map.get(key);
            for(String child : node.children.keySet()) {
                set.add(child);
            }
        }
        return set;
    }

    protected static class Dependency {
        String root, dependent;

        public Dependency(String root, String dependent) {
            this.root = root;
            this.dependent = dependent;
        }

        public String toString() {
            return root + "->" + dependent;
        }
    }

    protected static class GraphNode {
        String value;
        HashMap<String, GraphNode> children = new HashMap<String, GraphNode>();

        public GraphNode(String value) {
            this.value = value;
        }

        public void addChild(GraphNode g) {
            children.put(g.value, g);
        }

    }
}
