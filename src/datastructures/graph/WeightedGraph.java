package datastructures.graph;

import datastructures.linear.HashMap;
import datastructures.linear.LinkedList;

/**
 * A generic implementation of a weighted graph using adjacency lists.
 * @author ShaheerZK
 */
public class WeightedGraph<T>
{
    private class Edge<T> 
    {
        T dest;
        int weight;
        public Edge (T dest, int weight)
        {
            this.dest = dest;
            this.weight = weight;
        }
    }
    
    private HashMap<T, LinkedList<Edge<T>>> map = new HashMap<>();
    private int vertices = 0, edges = 0;
    private final boolean isDirected;
    
    public WeightedGraph (boolean isDirected)
    {
        this.isDirected = isDirected;
    }
    /**
     * Adds a vertex to the graph if it doesn't exist already.
     * @param data 
     */
    public void addVertex(T data)
    {
        if (map.containsKey(data))
            return;
        map.put(data, new LinkedList<>());
        vertices++;
    }
    /**
     * Adds a new edge between the src and dest with given weight. If the graph is undirected, both the nodes are linked together.
     * @param src
     * @param dest
     * @param weight 
     */
    public void addEdge(T src, T dest, int weight)
    {
        if (!map.containsKey(src) || !map.containsKey(dest))
            return;
        map.get(src).add(new Edge(dest, weight));
        if (!isDirected)
            map.get(dest).add(new Edge(src, weight));
        edges++;
    }
    
    public void removeVertex(T data)
    {
        
    }
    public void removeEdge(T src, T dest)
    {
        
    }
    /**
    * Returns the total number of edges in the graph.
    * @return 
    */
    public int getEdgeCount()
    { 
        return edges;
    }
    /**
    * Returns the total number of vertices in the graph.
    * @return 
    */
    public int getVertexCount()
    {
        return vertices;
    }
    /**
    * Returns whether the graph is directed or undirected.
    * @return 
    */
    public boolean isDirected()
    {
        return isDirected;
    }
}
