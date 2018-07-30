package com.learn.example.misc;

import java.util.LinkedList;
import java.util.Queue;

class Node {
	int data;
	Node left, right;
	public Node(int item) {
		left = null;
		right = null;
		data = item;
	}
}

public class BreadthFirstSearch {
	Node root;
	
	void breadthFirstSearch() {
		Queue<Node> queue = new LinkedList<Node>() ;
		if(root == null) {
			return;
		} else {
			queue.add(root);
		}
		
		while(!queue.isEmpty()) {
			Node n = queue.remove();
			System.out.println(n.data);
			if(n.left != null) queue.add(n.left);
			if(n.right != null) queue.add(n.right);
		}
	}
	
	public static void main(String[] args) {
		BreadthFirstSearch tree = new BreadthFirstSearch();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		
		System.out.println("Bread first search");
		tree.breadthFirstSearch();
	}
}
