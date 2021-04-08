// binary Tree node 

//public methods: 
//- getData() 
//- setData() 
//- getLeft() 
//- setLeft()
//- getRight()
//- setRight() 
//- printInOrder() 
//- insert() 
//- contains()
//- remove() 

package ctci.treesandgraphs;

public class TreeNode<T extends Comparable<T>>{
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;

	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TreeNode<T> getLeft() {
		return this.left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public TreeNode<T> getRight() {
		return this.right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public void printInorder() {
		if (this.left != null) {
			this.left.printInorder();
		}
		System.out.print(this.data + ", ");
		if (this.right != null) {
			this.right.printInorder();
		}
	}

	public void insert(T data) {
		if (data.compareTo(this.data) <= 0) {
			if (this.left == null) {
				this.left = new TreeNode<T>(data, null, null);
			} else {
				this.left.insert(data);
			}
		} else {
			if (this.right == null) {
				this.right = new TreeNode<T>(data, null, null);
			} else {
				this.right.insert(data);
			}
		}
	}

	public boolean contains(T data) {
		if (data.equals(this.data)) {
			return true;
		} else if (data.compareTo(this.data) <= 0) {
			if (this.left == null) {
				return false;
			}
			return this.left.contains(data);
		} else {
			if (this.right == null) {
				return false;
			}
			return this.right.contains(data);
		}
	}
	
	// removes the first element (starting from root) that matches data
	// returns true if an element is removed, else returns false 
	// if ensureMaxHeap is true, the 
	public boolean remove (T data, boolean ensureMinHeap) {
		
	}

}
