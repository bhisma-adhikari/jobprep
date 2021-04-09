//PUBLIC METHODS: 
//	- contains() --> bool 
//	- insert()   --> void 
//	- remove()   --> bool 

package ctci.treesandgraphs;

import ctci.treesandgraphs.TreeNode;

public class BinarySearchTree<T extends Comparable<T>> {
	private TreeNode<T> root;

	public TreeNode<T> getRoot() {
		return this.root;
	}

	// inserts data as a decendent node of ancestor
	// assumption: ancestor is not null
	private void insert(T data, TreeNode<T> ancestor) {
		if (data.compareTo(ancestor.getData()) <= 0) {
			if (ancestor.getLeft() == null) {
				ancestor.setLeft(new TreeNode<T>(data, null, null));
			} else {
				insert(data, ancestor.getLeft());
			}
		} else {
			if (ancestor.getRight() == null) {
				ancestor.setRight(new TreeNode<T>(data, null, null));
			} else {
				insert(data, ancestor.getRight());
			}
		}
	}

	// allows duplicate
	// Time : O(logn)
	public void insert(T data) {
		if (this.root == null) {
			this.root = new TreeNode<T>(data, null, null);
			return;
		}
		insert(data, this.root);
	}

	private boolean contains(T data, TreeNode<T> ancestor) {
		if (ancestor == null) {
			return false;
		}
		int comp = data.compareTo(ancestor.getData());
		if (comp == 0) {
			return true;
		} else if (comp < 0) {
			return contains(data, ancestor.getLeft());
		} else {
			return contains(data, ancestor.getRight());
		}
	}

	// Time : O(logn)
	public boolean contains(T data) {
		// TreeNode also has contains() method, but that is O(n). So, we don't use that.
		return contains(data, this.root);
	}

	// this class is created to return value from getNodeWithParentForMatchingData()
	private class NodeWithParent<T extends Comparable<T>> {
		private TreeNode<T> node;
		private TreeNode<T> parent;

		private NodeWithParent(TreeNode<T> node, TreeNode<T> parent) {
			this.node = node;
			this.parent = parent;
		}
	}

	// returns the first node (beginning from ancestor) matching data
	// returns null if no such node found
	// assumption :root node i.e passed ancestor won't match
	private NodeWithParent<T> getMatchingNodeWithParent(T data, TreeNode<T> ancestor) {
		while (ancestor != null) {
			TreeNode<T> left = ancestor.getLeft();
			TreeNode<T> right = ancestor.getRight();
			if (left != null) {
				if (data.compareTo(left.getData()) == 0) {
					return new NodeWithParent<T>(left, ancestor);
				} else if (data.compareTo(left.getData()) < 0) {
					ancestor = left;
					continue;
				} else {
					ancestor = right;
					continue;
				}
			}
		}
		return null;
	}

	private NodeWithParent<T> getInOrderPredecessorWithParent(TreeNode<T> node) {
		if (node.getLeft() == null) {
			return null;
		}
		if (node.getLeft().getRight() == null) {
			return new NodeWithParent<>(node.getLeft(), node);
		}
		TreeNode<T> parent = node.getLeft();
		while (parent.getRight().getRight() != null) {
			parent = parent.getRight();
		}
		return new NodeWithParent<>(parent.getRight(), parent);

	}

	private void remove(TreeNode<T> node, TreeNode<T> parent) {
		// whether node is the left child of parent or not
		boolean left = node == parent.getLeft();

		// CASE I : target node has no children
		// simply delete the target node
		if (node.getLeft() == null && node.getRight() == null) {
			if (left) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		}

		// CASE II : target node has 1 child
		// replace the target node by its child
		else if ((node.getLeft() != null && node.getRight() == null)
				|| (node.getRight() != null && node.getLeft() == null)) {
			TreeNode<T> child = node.getLeft() != null ? node.getLeft() : node.getRight();
			if (left) {
				parent.setLeft(child);
			} else {
				parent.setRight(child);
			}
		}

		// CASE III: target node has 2 children
		// - copy inorder predecessor's (or inorder successor) value to target node
		// - recursively remove inorder predessor node (or inorder successor)
		else {
			NodeWithParent<T> ipwp = getInOrderPredecessorWithParent(node);
			node.setData(ipwp.node.getData());
			remove(ipwp.node, ipwp.parent);
		}
	}

	// removes the first occurrence, if any
	// returns true if a node is removed, else false
	public boolean remove(T data) {
		// special case : delete root node
		// root has no parent, and thus is handled as a special case
		if (this.root != null && this.root.getData().equals(data)) {
			NodeWithParent<T> ipwp = getInOrderPredecessorWithParent(this.root);
			if (ipwp != null) {
				this.root.setData(ipwp.node.getData());
				remove(ipwp.node, ipwp.parent);
				return true;
			} else {
				return false;
			}
		}

		NodeWithParent<T> nwp = getMatchingNodeWithParent(data, this.root);
		if (nwp == null) {
			return false;
		}

		remove(nwp.node, nwp.parent);
		return true;
	}
}
