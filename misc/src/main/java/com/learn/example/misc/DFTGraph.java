package com.learn.example.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFTGraph {

	public static void depthFirstTraveral(Graph graph, int v, int src) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(src);
		boolean[] visited = new boolean[v];
		visited[src] = true;

		while (!stack.isEmpty()) {
			Integer n = stack.pop();
			
			System.out.print(n + " -> ");
			for (Integer pCrawl : graph.adjListArray[n]) {
				if (visited[pCrawl] == false) {
					stack.push(pCrawl);
					visited[pCrawl] = true;
				}
			}
		}
	}

	public static void findPath(Graph graph, int v, int src, int dst) {
		boolean[] visited = new boolean[v];
		//visited[src] = true;
		List<Integer> localPath = new ArrayList();
		localPath.add(src);
		findPath(graph, v, src, dst, visited, localPath);
	}
	
	public static void findPath(Graph graph, int v, int src, int dst, boolean[] visited, List<Integer> localPath) {
		
		visited[src] = true;
		
		if(src == dst) {
			System.out.println(localPath);
		}
		
		for(Integer i : graph.adjListArray[src]) {
			if(visited[i] == false) {
				localPath.add(i);
				findPath(graph, v, i, dst, visited, localPath);
				localPath.remove(i);
				
			}
		}
		
		visited[src] = false;
		
	}

	public static void depthFirstTraveral2(Graph graph, int v, int src) {
		boolean[] visited = new boolean[v];
		visited[src] = true;
		breadthFirstTraveralRecursive(graph, v, src, visited);

	}

	public static void breadthFirstTraveralRecursive(Graph graph, int v, int src, boolean[] visited) {
		visited[src] = true;
		System.out.print(src + " -> ");
		for (Integer pCrawl : graph.adjListArray[src]) {
			if (visited[pCrawl] == false) {
				
				breadthFirstTraveralRecursive(graph, v, pCrawl, visited);
			}
		}
	}

	public static void main(String[] args) {
		// create the graph given in above figure
		int V = 5;
		Graph graph = new Graph(V);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		//graph.addEdge(3, 3);
		graph.addEdge(3, 1);
		graph.addEdge(2, 1);
		graph.addEdge(2, 4);
		graph.addEdge(4, 3);
		graph.addEdge(4, 1);

		// print the adjacency list representation of
		// the above graph
		graph.printGraph();
		depthFirstTraveral(graph, V, 2);
		System.out.println("");
		depthFirstTraveral2(graph, V, 2);
		System.out.println("");
		System.out.println("Paths");
		findPath(graph, V, 2, 1);
	}

}
