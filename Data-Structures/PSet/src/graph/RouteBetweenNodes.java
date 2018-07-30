package graph;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//Given a directed graph, design an algorithm to determine whether there is a route between 2 nodes
//Uses Graph and GraphNode class from DataStructures project
public class RouteBetweenNodes {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.DConnect(0,1);
        g.DConnect(0,3);
        g.DConnect(0,4);
        g.DConnect(1,4);
        g.DConnect(1,5);
        g.DConnect(5, 2);
        g.DConnect(5,3);
        g.DConnect(1,1);
        g.DConnect(2, 1);


        System.out.println(g);
        System.out.println("Route between 0 and 5? " + routeBetweenNodes(g, 0, 5)); //T
        System.out.println("Route between 2 and 7? " + routeBetweenNodes(g, 2, 7)); //F
        System.out.println("Route between 10 and 3? " + routeBetweenNodes(g, 10, 3));   //F
        System.out.println("Route between 2 and 5? " + routeBetweenNodes(g, 0, 5)); //T
        System.out.println("Route between 1 and 1? " + routeBetweenNodes(g, 1, 1)); //T
        System.out.println("Route between 2 and 2? " + routeBetweenNodes(g, 2, 2)); //T
        System.out.println("Route between 0 and 3? " + routeBetweenNodes(g, 0, 3)); //T
    }

    /* Both Breadth first search or depth first search can be used for a problem like this
     * Since the graph is undirected, we may potentially have to look thru all of the nodes.
     * We cannot do bidirectional search since if there is a route from 0 to 2, it does not necessarily mean there is a path
     * from 2 to 0.
     * BFS and DFS has O(N) runtime where N is the number of nodes connected the starting node.
     * If node is not connected or is the last one checked, we have to look thru N nodes
     */
    public static boolean routeBetweenNodes(Graph g, int start, int end) {
        //run BFS because we don't want to travel too far away from the start if we don't have to
        HashSet<Integer> set = new HashSet<Integer>();
        Queue<GraphNode> q = new LinkedList<GraphNode>();

        //put root/start in the q to start
        if((g.getNode(start) == null) || (g.getNode(end) == null))
            return false;
        q.add(g.getNode(start));

        while(! q.isEmpty()) {
            GraphNode node = q.remove();
            if(node.getValue() == end) //found it
                return true;
            set.add(node.getValue());

            //add children to q
            for(Integer child : node.getChildren().keySet()) {
                if(! set.contains(child))
                    q.add(node.getChildren().get(child));
            }
        }
        //looked thru all children and q is empty
        return false;
    }
}
