package com.learn.example.misc;

import java.util.Iterator;
//Java Program to demonstrate adjacency list 
//representation of graphs
import java.util.LinkedList;

public class Graph
 {
     int V;
     LinkedList<Integer> adjListArray[];
      
     // constructor 
     Graph(int V)
     {
         this.V = V;
          
         // define the size of array as 
         // number of vertices
         adjListArray = new LinkedList[V];
          
         // Create a new list for each vertex
         // such that adjacent nodes can be stored
         for(int i = 0; i < V ; i++){
             adjListArray[i] = new LinkedList<Integer>();
         }
     }
     
     // Adds an edge to an undirected graph
     void addEdge(int src, int dest)
     {
         // Add an edge from src to dest. 
         adjListArray[src].add(dest);
          
         // Since graph is undirected, add an edge from dest
         // to src also
         //adjListArray[dest].addFirst(src);
     }

     void printGraph()
     {       
         for(int v = 0; v < V; v++)
         {
             System.out.println("Adjacency list of vertex "+ v);
             System.out.print("head");
             for(Integer pCrawl: adjListArray[v]){
                 System.out.print(" -> "+pCrawl);
             }
             System.out.println("\n");
         }
     }
     
     void DFSUtil(int v,boolean visited[])
     {
         // Mark the current node as visited and print it
         visited[v] = true;
         System.out.print(v+" ");
  
         // Recur for all the vertices adjacent to this vertex
         Iterator<Integer> i = adjListArray[v].listIterator();
         while (i.hasNext())
         {
             int n = i.next();
             if (!visited[n])
                 DFSUtil(n, visited);
         }
     }
  
     // The function to do DFS traversal. It uses recursive DFSUtil()
     void DFS(int v)
     {
         // Mark all the vertices as not visited(set as
         // false by default in java)
         boolean visited[] = new boolean[V];
  
         // Call the recursive helper function to print DFS traversal
         DFSUtil(v, visited);
     }
     
  // prints BFS traversal from a given source s
     void BFS(int s)
     {
         // Mark all the vertices as not visited(By default
         // set as false)
         boolean visited[] = new boolean[V];
  
         // Create a queue for BFS
         LinkedList<Integer> queue = new LinkedList<Integer>();
  
         // Mark the current node as visited and enqueue it
         visited[s]=true;
         queue.add(s);
  
         while (queue.size() != 0)
         {
             // Dequeue a vertex from queue and print it
             s = queue.poll();
             System.out.print(s+" ");
  
             // Get all adjacent vertices of the dequeued vertex s
             // If a adjacent has not been visited, then mark it
             // visited and enqueue it
             Iterator<Integer> i = adjListArray[s].listIterator();
             while (i.hasNext())
             {
                 int n = i.next();
                 if (!visited[n])
                 {
                     visited[n] = true;
                     queue.add(n);
                 }
             }
         }
     }

     public static void main(String args[])
     {
         // create the graph given in above figure
         Graph graph = new Graph(4);
         
         graph.addEdge(0, 1);
         graph.addEdge(0, 2);
         graph.addEdge(1, 2);
         graph.addEdge(2, 0);
         graph.addEdge(2, 3);
         graph.addEdge(3, 3);
         
         // print the adjacency list representation of 
         // the above graph
         graph.printGraph();
         System.out.println("BFS");
         graph.BFS(2);
         System.out.println("");
         System.out.println("DFS");
         graph.DFS(2);
     }
 }
 
