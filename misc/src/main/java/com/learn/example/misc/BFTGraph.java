package com.learn.example.misc;

import java.util.LinkedList;
import java.util.Queue;

public class BFTGraph {
	
	public static void breadthFirstTraveral(Graph graph, int v, int src) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		boolean[] visited = new boolean[v];
		visited[src] = true;
		
		while(!queue.isEmpty()) {
			Integer n = queue.poll();
			System.out.print(n + " -> ");
			for(Integer pCrawl: graph.adjListArray[n]){
                if(visited[pCrawl] == false) {
                	queue.add(pCrawl);
                	visited[pCrawl] = true;
                }
            }
		}
	}
	
	
	public static void main(String[] args) {
        // create the graph given in above figure
        int V = 4;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        
      
        // print the adjacency list representation of 
        // the above graph
        graph.printGraph();
        breadthFirstTraveral(graph, V, 2);
        
	}

}
