package ctci.treesandgraphs;

import ctci.treesandgraphs.TreeNode;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import ctci.treesandgraphs.Graph;
import ctci.treesandgraphs.GraphNode;

public class TreesAndGraphs {
	public static void main(String[] args) {
//		Integer[] arr = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
//		TreeNode<Integer> tn = createMinimalBST(arr);
//		tn.printInorder();
//		System.out.println(); 
//		System.out.println(isBST(tn));

		TreeNode<Integer> tn1 = new TreeNode<>(10, null, null);
		TreeNode<Integer> tn2 = new TreeNode<>(5, null, null);
		TreeNode<Integer> tn3 = new TreeNode<>(-3, null, null);
		TreeNode<Integer> tn4 = new TreeNode<>(3, null, null);
		TreeNode<Integer> tn5 = new TreeNode<>(2, null, null);
		TreeNode<Integer> tn6 = new TreeNode<>(11, null, null);
		TreeNode<Integer> tn7 = new TreeNode<>(3, null, null);
		TreeNode<Integer> tn8 = new TreeNode<>(-2, null, null);
		TreeNode<Integer> tn9 = new TreeNode<>(1, null, null);
		

		tn1.setLeft(tn2);
		tn1.setRight(tn3);
		tn2.setLeft(tn4);
		tn2.setRight(tn5);
//		tn3.setLeft(tn6);
		tn3.setRight(tn6);
		tn4.setLeft(tn7);
		tn4.setRight(tn8);
		tn5.setRight(tn9);
		
//		tn1.printInorder();
		
		List<List<Integer>> paths = paths(tn1); 
		for (List<Integer> path : paths) {
			System.out.println(path);
		}
		

//		tn1.printInorder();
//		System.out.println();
//
//		System.out.println(isBST(tn1));
//		System.out.println(isBST_1(tn1));
//		System.out.println(isBST_2(tn1));

//		buildOrder();
//		buildOrder_1();
		
	
//		List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2));
//		List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4));
//		
//		List<List<Integer>> weaved = weaveLists(list1, list2); 
//		
//		for (List<Integer> w : weaved) {
//			System.out.println(w);
//		}

	}
	
	public static<T extends Comparable<T>> List<List<T>> paths(TreeNode<T> root) {
		List<List<T>> ll = new LinkedList<>();
		if (root == null) {
			return ll; 
		}
		
		List<T> l = new LinkedList<>(); 
		l.add(root.getData()); 
		ll.add(l);
		
		// both children null
		if (root.getLeft() == null && root.getRight() == null) {
			return ll; 
		}
		// one child null
		if ((root.getLeft() != null && root.getRight() == null) || (root.getLeft() == null && root.getRight() != null)) {
			TreeNode<T> notNullChild = root.getLeft() != null ? root.getLeft() : root.getRight(); 
			List<List<T>> pathsFromChild = paths(notNullChild); 
			List<List<T>> pathsThruChild = prependToLists(root.getData(), pathsFromChild);  // start from root
			ll.addAll(pathsFromChild); 
			ll.addAll(pathsThruChild);
			return ll; 
		}
		// both children not null 
		List<List<T>> pathsFromLeftChild = paths(root.getLeft()); 
		List<List<T>> pathsThruLeftChild = prependToLists(root.getData(), pathsFromLeftChild); 
		List<List<T>> pathsFromRightChild = paths(root.getRight()); 
		List<List<T>> pathsThruRightChild = prependToLists(root.getData(), pathsFromRightChild);
		ll.addAll(pathsFromLeftChild);
		ll.addAll(pathsThruLeftChild);
		ll.addAll(pathsFromRightChild);
		ll.addAll(pathsThruRightChild); 
		return ll; 
	}
	
	
	// returns all possible lists such that the order of elements in both l1 and l2 
	// are still the same in the returned lists as well. 
	public static List<List<Integer>> weaveLists(List<Integer> l1, List<Integer> l2) {
		List<List<Integer>> res = new LinkedList<>();
		
		// base case 
		if (l1.isEmpty()) {
			res.add(new ArrayList<>(l2));
			return res;
		}

		// base case 
		if (l2.isEmpty()) {
			res.add(new ArrayList<>(l1));
			return res;
		}

		// handle recursive cases 
		Integer l1Head = l1.get(0);
		Integer l2Head = l2.get(0);

		List<Integer> l1Rest = new ArrayList<>(l1.subList(1, l1.size()));
		List<Integer> l2Rest = new ArrayList<>(l2.subList(1, l2.size()));

		res.addAll(prependToLists(l1Head, weaveLists(l1Rest, l2)));
		res.addAll(prependToLists(l2Head, weaveLists(l2Rest, l1))); 
		
		return res; 
	}



	public static<T extends Comparable<T>> List<List<T>> prependToLists(T t, List<List<T>> lists) {
		List<List<T>> res = new LinkedList<>();
		for (List<T> list : lists) {
			res.add(prependToList(t, list));
		}
		return res; 
	}
	
	
	
	public static<T extends Comparable<T>> List<T> prependToList(T t, List<T> list){
		List<T> res = new LinkedList<>(list); 
		res.add(0, t);
		return res; 
	}

	// uses DFS
	public static void buildOrder_1() {
		String[] projects = { "a", "b", "c", "d", "e", "f" };
		String[][] dependencies = { { "a", "d" }, { "f", "b" }, { "b", "d" }, { "f", "a" }, { "d", "c" } };
//		String[][] dependencies = { { "a", "d" }, { "f", "b" }, { "b", "d" }, { "f", "a" }, { "d", "c" }, {"c", "b"} }; // cycle 

		Graph graph = buildGraph(projects, dependencies);

		Map<String, State> map = new HashMap<>();
		for (String s : projects) {
			map.put(s, State.UNPROCESSED);
		}

		Stack<String> order = new Stack<>();
		List<String> unprocessedNodeNames = getUnprocessedNodeNames(map);
		while (!unprocessedNodeNames.isEmpty()) {
			GraphNode node = graph.getNodeByName(unprocessedNodeNames.get(0));
			processNode(node, order, map);
			unprocessedNodeNames = getUnprocessedNodeNames(map);
		}
//		System.out.println(order);
		List<String> ans = new LinkedList<>(); 
		while (!order.isEmpty()) {
			ans.add(order.pop()); 
		}
		System.out.println(ans);
		

	}

	// returns true if node is processed successfully
	// otherwise returns false (eg. if cycle exists)
	private static boolean processNode(GraphNode node, Stack<String> order, Map<String, State> map) {
//		System.out.println(node.getName() + " " + map);
//		System.out.println(order);
		if (map.get(node.getName()) == State.PROCESSING) {
			System.out.println("error: cycle exists!");
			return false;
		}

		// base case
		if (node.getChildren().isEmpty()) {
			order.push(node.getName());
			map.put(node.getName(), State.PROCESSED);
			return true;
		}

		map.put(node.getName(), State.PROCESSING);
		for (GraphNode n : node.getChildren()) {
			if (map.get(n.getName()) != State.PROCESSED) {
				processNode(n, order, map);
			}
		}
		order.push(node.getName());
		map.put(node.getName(), State.PROCESSED);
		return true;
	}

	private static List<String> getUnprocessedNodeNames(Map<String, State> map) {
		List<String> unprocessed = new ArrayList<>();
		for (String name : map.keySet()) {
			if (map.get(name) == State.UNPROCESSED) {
				unprocessed.add(name);
			}
		}
		return unprocessed;
	}

	private enum State {
		UNPROCESSED, PROCESSING, PROCESSED
	}

	private static Graph buildGraph(String[] projects, String[][] dependencies) {
		Graph graph = new Graph();
		// create nodes
		for (String p : projects) {
			graph.addNode(new GraphNode(p, null));
		}
		// set children for all children
		for (String[] d : dependencies) {
			GraphNode indep = graph.getNodeByName(d[0]);
			GraphNode dep = graph.getNodeByName(d[1]);
			indep.getChildren().add(dep);
		}
		return graph;
	}

	public static void buildOrder() {
		List<Character> vertices = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));
		List<List<Character>> dependencies = new ArrayList<>();
		dependencies.add(new ArrayList<>(Arrays.asList('a', 'd')));
		dependencies.add(new ArrayList<>(Arrays.asList('f', 'b')));
		dependencies.add(new ArrayList<>(Arrays.asList('b', 'd')));
		dependencies.add(new ArrayList<>(Arrays.asList('f', 'a')));
		dependencies.add(new ArrayList<>(Arrays.asList('d', 'c')));

//		dependencies.add(new ArrayList<>(Arrays.asList('c', 'b'))); // cycle 

		Map<Character, List<Character>> map = new HashMap<>(); // key:project, value: dependencies
		for (Character c : vertices) {
			map.put(c, new LinkedList<>());
		}
		for (List<Character> dependency : dependencies) {
			Character independent = dependency.get(0);
			Character dependent = dependency.get(1);
			map.get(dependent).add(independent);
		}

		LinkedList<Character> soln = new LinkedList<>();

		while (!map.isEmpty()) {
			Set<Character> removedKeys = new HashSet<>(map.keySet()); // will be updated
			// remove keys with empty values i.e. project without dependencies
			map.entrySet().removeIf(entry -> entry.getValue().isEmpty());
			Set<Character> newKeys = new HashSet<>(map.keySet());
			removedKeys.removeAll(newKeys);
			System.out.println("Removed keys: " + removedKeys);
			if (removedKeys.isEmpty()) {
				System.out.println("Cycle exists!");
				return;
			}
			soln.addAll(removedKeys);
			for (Character c : map.keySet()) {
				// remove dependencies which are in removedKeys i.e. those projects which have
				// just been built
				map.get(c).removeIf(entry -> removedKeys.contains(entry));
			}
		}
		System.out.println(soln);
	}

	public static <T extends Comparable<T>> boolean isBST_2(TreeNode<T> root, T min, T max) {
		if (root == null) {
			return true;
		}

		if (min != null && root.getData().compareTo(min) <= 0) {
			return false;
		}

		if (max != null && root.getData().compareTo(max) > 0) {
			return false;
		}

		return isBST_2(root.getLeft(), min, root.getData()) && isBST_2(root.getRight(), root.getData(), max);
	}

	public static <T extends Comparable<T>> boolean isBST_2(TreeNode<T> root) {
		return isBST_2(root, null, null);
	}

	private static <T extends Comparable<T>> boolean isBST_1(TreeNode<T> root, Stack<T> inOrderStack) {
		if (root == null) {
			return true;
		}
		if (!isBST_1(root.getLeft(), inOrderStack)) {
			return false;
		}

		if (!inOrderStack.isEmpty() && inOrderStack.peek().compareTo(root.getData()) > 0) { // top item in stack > root
			return false;
		}

		inOrderStack.push(root.getData());
		System.out.println(inOrderStack);

		if (!isBST_1(root.getRight(), inOrderStack)) {
			return false;
		}

		return true;
	}

	private static class TWrapper<T extends Comparable<T>> {
		private T val;

		private TWrapper(T val) {
			this.val = val;
		}
	}

	private static <T extends Comparable<T>> boolean isBST_1(TreeNode<T> root, TWrapper<T> lastElem) {

//		System.out.println(root + " " +lastElem.val);
		if (root == null) {
			return true;
		}
		if (!isBST_1(root.getLeft(), lastElem)) {
			return false;
		}

		// rightmost element of left subtree of root must be <= root
		if (lastElem.val != null && lastElem.val.compareTo(root.getData()) > 0) {
			return false;
		}
		lastElem.val = root.getData();

		if (!isBST_1(root.getRight(), lastElem)) {
			return false;
		}

		return true;
	}

	// faster, because terminates as soon as a new element is added in inOrderLL
	// which is less than last element added to it
	public static <T extends Comparable<T>> boolean isBST_1(TreeNode<T> root) {
//		return isBST_1(root, new Stack<T>());
		TWrapper lastElem = new TWrapper(null);
		return isBST_1(root, lastElem);
	}

	// updates inOrderLL
	private static <T extends Comparable<T>> void inOrder(TreeNode<T> root, LinkedList<T> inOrderLL) {
		if (root == null) {
			return;
		}
		inOrder(root.getLeft(), inOrderLL);
		inOrderLL.add(root.getData());
		inOrder(root.getRight(), inOrderLL);
	}

	public static <T extends Comparable<T>> boolean isBST(TreeNode<T> root) {
		LinkedList<T> inOrderLL = new LinkedList<>();
		inOrder(root, inOrderLL);
		// check if inOrderLL is sorted
		if (!inOrderLL.isEmpty()) {
			Iterator<T> itr = inOrderLL.iterator();
			T prev = itr.next(); // first element
			T curr = null;
			while (itr.hasNext()) {
				curr = itr.next();
				if (curr.compareTo(prev) == -1) { // curr < prev
					return false;
				}
				prev = curr;
			}
		}
		System.out.println(inOrderLL);
		return true;
	}

	// uses bfs, but shorter code than listOfDepth()
	public static <T extends Comparable<T>> LinkedList<LinkedList<TreeNode<T>>> listOfDepths_2(TreeNode<T> root) {
		LinkedList<LinkedList<TreeNode<T>>> res = new LinkedList<>();
		LinkedList<TreeNode<T>> curr = new LinkedList<>();
		curr.add(root);

		while (curr.size() > 0) {
			res.add(curr);
			LinkedList<TreeNode<T>> parents = curr;
			curr = new LinkedList<TreeNode<T>>();
			for (TreeNode<T> p : parents) {
				if (p.getLeft() != null) {
					curr.add(p.getLeft());
				}
				if (p.getRight() != null) {
					curr.add(p.getRight());
				}
			}
		}
		return res;

	}

	private static <T extends Comparable<T>> void listOfDepths_1(TreeNode<T> root, int depth,
			LinkedList<LinkedList<TreeNode<T>>> res) {
		// add new list for the depth, if needed
		if (root == null) {
			return;
		}

		if (res.size() < depth + 1) {
			res.add(new LinkedList<TreeNode<T>>());
		}

		res.get(depth).add(root);
		listOfDepths_1(root.getLeft(), depth + 1, res);
		listOfDepths_1(root.getRight(), depth + 1, res);

	}

	// uses depth first search
	public static <T extends Comparable<T>> LinkedList<LinkedList<TreeNode<T>>> listOfDepths_1(TreeNode<T> root) {
		LinkedList<LinkedList<TreeNode<T>>> res = new LinkedList<>();
		listOfDepths_1(root, 0, res);
		return res;
	}

	// uses breadth first search
	public static <T extends Comparable<T>> LinkedList<LinkedList<TreeNode<T>>> listOfDepths(TreeNode<T> root) {
		LinkedList<LinkedList<TreeNode<T>>> res = new LinkedList<>();
		if (root != null) {
			res.add(new LinkedList<TreeNode<T>>()); // first LL (currently empty)

			LinkedList<TreeNodeWithDepth<T>> queue = new LinkedList<>();
			int depth = 0; // depth of currently building linked list of res
			queue.add(new TreeNodeWithDepth<>(root, 0));
			while (!queue.isEmpty()) {
				TreeNodeWithDepth<T> tnd = queue.removeFirst();
				if (tnd.depth != depth) { // tnd.depth = depth + 1
					// depth has increased, so create new LL
					res.add(new LinkedList<TreeNode<T>>());
					depth++;
				}
				res.getLast().add(tnd.node);
				if (tnd.node.getLeft() != null) {
					queue.add(new TreeNodeWithDepth<T>(tnd.node.getLeft(), tnd.depth + 1));
				}
				if (tnd.node.getRight() != null) {
					queue.add(new TreeNodeWithDepth<>(tnd.node.getRight(), tnd.depth + 1));
				}
			}
		}
		return res;

	}

	private static class TreeNodeWithDepth<T extends Comparable<T>> {
		private TreeNode<T> node;
		private int depth;

		private TreeNodeWithDepth(TreeNode<T> node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}

	private static <T extends Comparable<T>> TreeNode<T> createMinimalBST(T[] arr, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return null;
		}
		int midIndex = ((endIndex - startIndex + 1) % 2) == 1 ? (startIndex + endIndex) / 2
				: (startIndex + endIndex) / 2 + 1;
		return new TreeNode<T>(arr[midIndex], createMinimalBST(arr, startIndex, midIndex - 1),
				createMinimalBST(arr, midIndex + 1, endIndex));
	}

	public static <T extends Comparable<T>> TreeNode<T> createMinimalBST(T[] arr) {
		return createMinimalBST(arr, 0, arr.length - 1);
	}

	// used by isBalanced_1()
	private static <T extends Comparable<T>> int checkHeight(TreeNode<T> root) {
		// returns -1 if tree is not balanced, else returns the height of tree
		if (root == null) {
			return 0;
		}
		int left = checkHeight(root.getLeft());
		if (left == -1)
			return -1;
		int right = checkHeight(root.getRight());
		if (right == -1)
			return -1;
		if (Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}

	// faster
	public static <T extends Comparable<T>> boolean isBalanced_1(TreeNode<T> root) {
		return checkHeight(root) != -1;
	}

	public static <T extends Comparable<T>> boolean isBalanced(TreeNode<T> root) {
		if (root == null) {
			return true;
		}

		return isBalanced(root.getLeft()) && isBalanced(root.getRight())
				&& Math.abs(depth(root.getLeft()) - depth(root.getRight())) <= 1;
	}

	// returns depth of tree with given root
	public static <T extends Comparable<T>> int depth(TreeNode<T> root) {
		if (root == null) {
			return 0;
		}
		return Math.max(depth(root.getLeft()), depth(root.getRight())) + 1;
	}

}
