
/* 
 * File:     DGraph.java
 * Purpose:  This class creates an directed graph objects
 *           whose vertices are cites and edge weigts are 
 *           the cost of travel between the cities. 
 *              
 * Course #: CS 210, Spring 2022
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DGraph.java
 * 
 * Represents a directed graph. The nodes are represented as
 * integers ranging from 1 to num_nodes inclusive.
 * The weights are assumed to be >= zero.
 * 
 * Usage instructions:
 * 
 * Construct a DGraph
 * DGraph graph = new DGraph(numNodes);
 * 
 * Add an edge
 * graph.addEdge(v, w, weight);
 * 
 * Other useful methods:
 * graph.getWeight(v,w)
 * graph.getNumNodes()
 * List<Integer> list = graph.getNeighbors(v);
 * 
 */
public class DGraph {
    private Set<Integer> vertices;
    private Set<Edge> edges;
    private int numNodes;


    /*
     * Constructs an instance of the DGraph class with # nodes numNodes.
     */
    public DGraph(int numNodes) {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
        this.numNodes = 0;
    }

    /**
     * Adds the directed edge (v,w) to the graph including updating the node
     * count appropriately.
     * 
     * @param v
     * @param w
     */
    public void addEdge(int v, int w, double distance) {
        vertices.add(v); vertices.add(w);
        Edge edge = new Edge(v, w, distance);
        edges.add(edge);
        numNodes ++;
    }

    /*
     * Returns the number of nodes in this graph.
     */
    public int getNumNodes() {
        return numNodes;
    }

    /*
     * Returns the number of vertices in this graph.
     */
    public int getNumVertices() {
        return vertices.size();
    }

    // Returns the weight for the given edge.
    // Returns -1 if there is no edge between the nodes v and w.
    public double getWeight(int v, int w) {
        for (Edge edge: edges) {
            if (v == edge.node1 && w == edge.node2) {
                return edge.weight;
            }
        }
        return -1;
    }

    /**
     * For a given node returns a sorted list of all its neighbors.
     * 
     * @param v
     *            Node identifier
     * @return A sorted list of v's neighbors.
     */
    public List<Integer> getNeighbors(int v) {
        List<Integer> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.node1 == v)
                neighbors.add(edge.node2);
        }
        neighbors.sort(null);
        return neighbors;
    }


    /* --------------------------------------- */
    /*
     * You should not need to touch anything below this line,
     * except for maybe the name edges in the for each loop just below
     * in the toDotString method if you named your collection of edges
     * differently.
     */
    // Create a dot representation of the graph.
    public String toDotString() {
        String dot_str = "digraph {\n";
        // Iterate over the edges in order.
        for (Edge e : edges) {
            dot_str += e.toDotString() + "\n";
        }
        return dot_str + "}\n";
    }



    /**
     * Immutable undirected edges.
     */
    public class Edge implements Comparable<Edge> {

        // Nodes in edge and weight on edge
        private final int node1;
        private final int node2;
        private final double weight;
        
        /**
         * Stores the given nodes with smaller id first.
         * 
         * @param node1
         * @param node2
         */
        public Edge(int node1, int node2, double weight) {
            assert weight >= 0.0;
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        /**
         * @return an directed edge string in dot format
         */
        public String toDotString() {
            return "" + node1 + " -> " + node2 + " [label=\"" + weight
                    + "\"];";
        }

        /**
         * Lexicographical ordering on edges (node1,node2).
         */
        public int compareTo(Edge other) {
            if (this.equals(other)) {
                return 0; // this and other are equal
            } else if ((node1 < other.node1)
                    || (node1 == other.node1 && node2 < other.node2)) {
                return -1; // this is less than other
            } else {
                return 1; // this is greater than other
            }
        }

        /**
         * Lexicographical ordering on edges (node1,node2).
         */
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) {
                return false;
            }
            Edge other = (Edge) o;  // has to be casted !REMEMBER
            return (node1 == other.node1) && (node2 == other.node2);
        }

        /**
         * Know number of nodes when read in input file, so can give each edge a
         * unique hash code.
         */
        public int hashCode() {
            return getNumNodes() * node1 + node2;
        }
    }

}
