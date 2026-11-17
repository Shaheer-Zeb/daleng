package datastructures.graph;

import datastructures.linear.ArrayList;
import datastructures.linear.HashMap;
import datastructures.linear.Queue;
import datastructures.linear.LinkedList;

/**
* A generic unweighted graph using adjacency lists.
* @author ShaheerZK
*/
public class UnweightedGraph<T>
{
	private HashMap<T, LinkedList<T>> map = new HashMap<>();
	private int vertices = 0, edges = 0;
        private final boolean isDirected;
      
        public UnweightedGraph(boolean isDirected)
        {
            this.isDirected = isDirected;
        }
        /**
         * Adds a new vertex to the graph if it doesn't exist already.
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
         * Adds a edge between two nodes, if the graph is directed, it links only src to dest, but if it's undirected, both are linked together.
         * @param src
         * @param dest 
        * @return  Returns whether the edge was added successfully.
         */
	public boolean addEdge(T src, T dest)
	{
		if (!map.containsKey(src) || !map.containsKey(dest))
			return false;
		map.get(src).add(dest);
                if (!isDirected)
                    map.get(dest).add(src);
		edges++;
                return true;
	}
        /**
         * Performs DFS and prints the edges (calls the toString() of the generic T type).
         * @param source 
         */
	public void dfs(T source)
	{
		HashMap<T, Integer> visited = new HashMap<>();
		if (!map.containsKey(source))
			return;
		dfsHelper(source, visited);
	}
	private void dfsHelper(T vertex, HashMap<T, Integer> visited)
	{
		visited.put(vertex, Integer.MIN_VALUE);
		System.out.print(vertex + " ");
		for (T neighbourVertex : map.get(vertex))
		{
			if (!visited.containsKey(neighbourVertex))
				dfsHelper(neighbourVertex, visited);
		}
	}
        /**
         * Performs BFS and calls the toString() method of T type for each vertex in the graph.
         * @param source 
         */
        public void bfs(T source)
        {
            if (!map.containsKey(source))
                return;
            Queue<T> queue = new Queue<>();
            HashMap<T, Integer> visited = new HashMap<>(); // I don't have a HashSet class, that's why I'm just using Integer in value pair as placeholder, it doesn't have any use actually.
            queue.enqueue(source);
            visited.put(source, Integer.MIN_VALUE);
            
            while (!queue.isEmpty())
            {
                T vertex = queue.dequeue();
                System.out.print(vertex + " ");
                for (T neighbour : map.get(vertex))
                {
                    if (!visited.containsKey(neighbour))
                    {
                        queue.enqueue(neighbour);
                        visited.put(neighbour, Integer.MIN_VALUE);
                    }
                }
            }
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
