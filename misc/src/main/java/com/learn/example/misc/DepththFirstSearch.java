package com.learn.example.misc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepththFirstSearch {
	Node root;
	
	void depthFirstSearch() {
		Stack<Node> stack = new Stack<Node>() ;
		if(root == null) {
			return;
		} else {
			stack.push(root);
		}
		
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			System.out.println(n.data);
			if(n.right != null) stack.push(n.right);
			if(n.left != null) stack.push(n.left);
			
		}
	}
	
	public static void main(String[] args) {
		DepththFirstSearch tree = new DepththFirstSearch();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);
		System.out.println("Bread first search");
		tree.depthFirstSearch();
	}
}
