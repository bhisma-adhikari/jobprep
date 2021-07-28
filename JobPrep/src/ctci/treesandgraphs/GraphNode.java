package ctci.treesandgraphs;

import java.util.List;
import java.util.ArrayList;

public class GraphNode {
	private String name;
	private List<GraphNode> children;

	public GraphNode(String name, List<GraphNode> children) {
		this.name = name;
		this.children = children != null ? children : new ArrayList<>();
	}
	
	public String getName() {
		return this.name; 
	}
	
	public List<GraphNode> getChildren() {
		return this.children; 
	}
}
