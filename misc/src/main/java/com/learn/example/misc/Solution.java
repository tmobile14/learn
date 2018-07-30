package com.learn.example.misc;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class Node {
        Node() {
            next = null;
        }
        Node(int i) {
            index = i;
            next = null;
        }
        Node next;
        Node last;
        int index;
    }
    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; ++i) {
            nodes[i] = new Node(i);
            nodes[i].last = nodes[i];
        }

        for(int i = 0; i < cities.length; ++i) {
            Node node = new Node(cities[i][1]-1);
            node.last = null;
            nodes[cities[i][0]-1].last.next = node;
            nodes[cities[i][0]-1].last = node;
            
            node = new Node(cities[i][0]-1);
            node.last = null;
            nodes[cities[i][1]-1].last.next = node;
            nodes[cities[i][1]-1].last = node;

            //graph[cities[i][0]-1][cities[i][1]-1] = 1;
            //graph[cities[i][1]-1][cities[i][0]-1] = 1;
        }
        
        /*
        for(int i = 0; i < n; ++i) {
            Node node = nodes[i];
            while(node != null) {
                System.out.print(node.index + " -> ");
                node=node.next;
            }
            
            System.out.println("");
        }
        */
        
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; ++i) visited[i] = false;
                
        long edges = 0;
        long disjoint = 0;
        
        for(int i = 0; i < n; ++i) {
            if(!visited[i]) {
                disjoint++;
                visited[i] = true;
                edges += countedges(nodes, visited, i);
                //System.out.println(Arrays.toString(visited));
            }
        }
        
        System.out.println("Edges: " + edges);
        System.out.println("Disjoin: " + disjoint);
        long rcost = (long)edges * (long)c_road + (long)c_lib * (long)disjoint;
        long lcost = (long)n * (long)c_lib;
        if(rcost < lcost) {
        	System.out.println(rcost);
            return rcost;
        } else {
        	System.out.println(lcost);
            return lcost;
            
        }
    }
    
    static int countedges(Node[] nodes, boolean[] visited, int x) {
        int edges = 0;
        Node n = nodes[x].next;
        while(n != null) {
            
            //System.out.println("Looking for edge: " + (x) + " , " + n.index);
            if(!visited[n.index]) {
                visited[n.index] = true;
                //System.out.println("Not visited");
                edges += 1 + countedges(nodes, visited, n.index);
                
            }
            n = n.next;
        }
        return edges;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("OUTPUT_PATH"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
