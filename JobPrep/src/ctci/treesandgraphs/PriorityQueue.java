// min priority queue 

package ctci.treesandgraphs;

public class PriorityQueue<T extends Comparable<T>> {
	private TreeNode<T> root;

	public PriorityQueue() {
		this.root = null;
	}

	public PriorityQueue(TreeNode<T> root) {
		this.root = root;
	}

	public TreeNode<T> getRoot() {
		return this.root;
	}

	public void printInOrder() {
		if (this.root != null) {
			this.root.printInorder();
		}
		System.out.println();
	}


	public void insert(T data) {
		if (this.root == null) {
			this.root = new TreeNode<T>(data, null, null); 
			return; 
		}
		 
		this.root.insert(data);
	}
	

	public boolean contains(T data) {
		return this.root.contains(data);
	}

	// removes the first occurrence (nearest to the root) of an element, if such element exists
	// returns true if an element is successfully removed, else false 
	public boolean remove(T data) {
		
	}
//	
//	// removes and returns the root element's data 
//	public T poll() {
//		
//	}
//	
//	// returns, without removing, the root element's data
//	public T peek() {
//		
//	}
}
