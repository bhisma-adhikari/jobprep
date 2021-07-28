package ctci.treesandgraphs;

import java.util.List; 
import java.util.ArrayList; 

public class Graph {
	private List<GraphNode> nodes ; 
	
	public Graph() {
		this.nodes = new ArrayList<>(); 
	}
	
	public Graph(List<GraphNode> nodes) {
		this.nodes = nodes != null ? nodes : new ArrayList<>(); 
	}
	
	// returns false if a node with same name already exists 
	public boolean addNode(GraphNode node) {
		if (this.getNodeByName(node.getName()) != null){
			return false; 
		}
		this.nodes.add(node); 
		return true; 
	}
	
	public List<GraphNode> getNodes() {
		return this.nodes; 
	}
	
	public GraphNode getNodeByName(String name) {
		for (GraphNode node : this.nodes) {
			if (node.getName() == name) {
				return node; 
			}
		}
		return null; 
	}
}
