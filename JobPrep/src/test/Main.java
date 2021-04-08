package test;

import java.util.*;

import ctci.treesandgraphs.PriorityQueue;
import ctci.treesandgraphs.TreeNode;

public class Main {
	public static void main(String[] args) throws Exception {
		TreeNode<Integer> tn1 = new TreeNode<Integer>(20, null, null); 
		TreeNode<Integer> tn2 = new TreeNode<Integer>(10, null, null); 
		TreeNode<Integer> tn3 = new TreeNode<Integer>(30, null, null); 
		TreeNode<Integer> tn4 = new TreeNode<Integer>(5, null, null); 
		TreeNode<Integer> tn5 = new TreeNode<Integer>(15, null, null); 
		TreeNode<Integer> tn6 = new TreeNode<Integer>(25, null, null); 
		
		tn1.setLeft(tn2);
		tn1.setRight(tn3);
		tn2.setLeft(tn4);
		tn2.setRight(tn5);
		tn3.setLeft(tn6);

	
		PriorityQueue<Integer> pq = new PriorityQueue<>(tn1); 
		pq.printInOrder();
		
		pq.insert(12);
		pq.printInOrder();
		System.out.println(pq.contains(230));
		
		
		
	}

}

class Solution {

}