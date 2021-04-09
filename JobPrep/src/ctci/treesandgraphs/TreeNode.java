// binary Tree node 

//public methods: 
// - getData() 
// - setData() 
// - getLeft() 
// - setLeft()
// - getRight()
// - setRight() 
// - toString() 
// - printInOrder() 
// - contains()
// - getInOrderPredecessor() : returns the rightmost node of left subtree
// - getInOrderSuccessor()   : returns the leftmost node of right subtree 

package ctci.treesandgraphs;

public class TreeNode<T extends Comparable<T>> {
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

	@Override
	public String toString() {
		return this == null ? null : this.data.toString();
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

	// Time: O(n)
	public boolean contains(T data) {
		return this.data.equals(data) || (this.left != null && this.left.contains(data))
				|| (this.right != null && this.right.contains(data));

	}

	// returns the rightmost node of left subtree
	public TreeNode<T> getInOrderPredessor() {
		if (this.left == null) {
			return null;
		}
		TreeNode<T> curr = this.left;
		while (curr.right != null) {
			curr = curr.right;
		}
		return curr;
	}

	// returns the leftmost node of right subtree
	public TreeNode<T> getInOrderSuccessor() {
		if (this.right == null) {
			return null;
		}
		TreeNode<T> curr = this.right;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}

}
