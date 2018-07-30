package com.learn.example.misc;

public class Dijkstras {
	
	
	public static void dijkstra(int[][] graph, int src) {
		int nVertices = graph[0].length;
		int[] shortestDistance = new int[nVertices];
		int[] parents = new int[nVertices];
		boolean[] visited = new boolean[nVertices];
		
		for(int i = 0 ; i < nVertices; ++i) {
			shortestDistance[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		
		parents[src] = -1;
		shortestDistance[src] = 0;
		//visited[src] = true;
		
		for(int i = 0 ; i < nVertices; ++i) {
			//find the vertices with the shortest path
			int shortestDist = Integer.MAX_VALUE;
			int shortestVert = -1;
			for(int j = 0; j < nVertices; ++j) {
				if(!visited[j] && shortestDistance[j] < shortestDist) {
					shortestDist = shortestDistance[j];
					shortestVert = j;
				}
			}
			
			System.out.println("ShotestVert " + shortestVert);
			visited[shortestVert] = true;
			
			for(int k = 0; k < nVertices; ++k) {
				int edgeDistance = graph[shortestVert][k];
				if((edgeDistance > 0) && (edgeDistance + shortestDist) < shortestDistance[k]) {
					shortestDistance[k] = edgeDistance + shortestDist;
					parents[k] = shortestVert;
				}
			}
		}
		
		printSolution(parents, shortestDistance, src);
	}
	
	public static void printSolution(int[] parents, int[] shortestDistance, int src) {
		System.out.println("Source -> Destination         Distance         Path");
		for(int i = 0; i < parents.length; ++i) {
			if(i != src) {
				System.out.print(src + " -> " + i);
				System.out.print("        " + shortestDistance[i] + "       ");
				printPath(parents, i);
				System.out.println("");
			}
		}
	}
	
	
	public static void printPath(int[] parents, int dest) {
		if(parents[dest] == -1) {
			System.out.print(dest + " ");
			return;
		}
		printPath(parents, parents[dest]);
		System.out.print(dest + " ");
	}
	
	// Driver Code
	public static void main(String[] args) {
		int[][] adjacencyMatrix = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 0, 10, 0, 2, 0, 0 }, { 0, 0, 0, 14, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
		dijkstra(adjacencyMatrix, 0);
	}

}
