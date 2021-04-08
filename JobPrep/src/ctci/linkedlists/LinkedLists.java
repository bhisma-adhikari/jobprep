package ctci.linkedlists;

//import java.util.*;
import ctci.datastructures.LinkedList;
import ctci.datastructures.LinkedListNode;
import ctci.datastructures.LL;
import ctci.datastructures.LLN;
import java.util.Stack;

public class LinkedLists {
	public static void main(String[] args) {
//		LL ll1 = new LL(new int[] {8, 1, 6});
//		LL ll2 = new LL(new int[] {5, 9, 4, 9});

		LLN lln1 = new LLN(1, null);
		LLN lln2 = new LLN(2, null); 
		LLN lln3 = new LLN(3, null); 
		LLN lln4 = new LLN(4, null); 
		LLN lln5 = new LLN(5, null); 
		LLN lln6 = new LLN(6, null);
		
		lln1.next = lln2; 
		lln2.next = lln3;
		lln3.next = lln4; 
		lln4.next = lln2; 
//		
//		lln5.next = lln3; 
		
		System.out.println(new Solution().loopNode_1(lln1));
		
	}
}

class Solution<T> {
	// Time: O(n) 
	// uses 2 pointers 
	public LLN loopNode_1(LLN head) {
		LLN fast = head; 
		LLN slow = head; 
		while (fast != null && fast.next != null) {
			fast = fast.next.next; 
			slow = slow.next; 
			if (fast == slow) {  // collision
				slow = head; 
				while (slow != fast) {
					slow = slow.next; 
					fast = fast.next; 
				}
				return slow; 
			}
		}
		return null; 
	}
	
	
	// Time : O(n^2)
	public LLN loopNode(LLN head) {
		LLN node1 = head; 
		while (node1.next != null) {
			LLN node2 = node1.next; 
			while (node2 != null) {
				if (node1.next == node2.next) {
					return node1.next;
				}
				node2 = node2.next; 
			}
			node1 = node1.next; 
		}
		return null; 
	}
	
	
	// Time: O(n) + O(m) 
	public boolean intersect_1 (LLN head1, LLN head2) {
		LLN tail1 = head1; 
		while(tail1.next != null) {
			tail1 = tail1.next; 
		}
		
		LLN tail2 = head2; 
		while(tail2.next != null) {
			tail2 = tail2.next; 
		}
		
		return tail1 == tail2;  
		
		
	}
	
	
	// Time O(mn)
	public boolean intersect(LLN head1, LLN head2) {
		while(head1 != null) {
			LLN node2 = head2; 
			while(node2 != null) {
				if (head1 == node2) {
					return true; 
				}
				node2 = node2.next; 
			}
			head1 = head1.next; 
		}
		return false; 
	}
	
	
	
	// uses recursion
	public boolean isPalindrome_2(LLN head) {
		Result result = isPalindrome_2Helper(head,  lengthLL(head)); 
		return result.result;
	}
	
	private Result isPalindrome_2Helper(LLN head, int len) {
		if (len == 0) {
			return new Result(true, head);
		}
		if (len == 1) {
			return new Result(true, head.next); 
		}
		
		Result res = isPalindrome_2Helper(head.next,  len - 2); 
		res.result &=  (head.data == res.node.data);
		res.node = res.node.next; 
		return res; 
	}

	private int lengthLL(LLN head) {
		int size = 0; 
		while (head != null) {
			size++; 
			head = head.next; 
		}
		return size; 
	}
	
	private class Result {
		private boolean result;
		private LLN node;

		private Result(boolean result, LLN node) {
			this.result = result;
			this.node = node;
		}
	}

	// reverses corresponding LL and returns the new head node
	public LLN reverse(LLN node) {
		if (node == null) {
			return null;
		}
		if (node.next == null) {
			return node;
		}
		LLN tail = new LLN(node.data, null);
		return append(reverse(node.next), tail);

	}

	// appends LL with head head2 to LL wth head head1
	public LLN append(LLN head1, LLN head2) {
		LLN curr = head1;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = head2;
		return head1;
	}

	// uses stack and 2 pointers
	public boolean isPalindrome_1(LLN head) {
		Stack<Integer> stack = new Stack<>();
		LLN fast = head;
		LLN slow = head;

		// first condition for even-length LL
		// second condition for odd-length LL
		while (fast != null && fast.next != null) {
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}
		System.out.println(stack);

		if (fast != null) { // odd length LL
			slow = slow.next;
		}

		while (!stack.empty()) {
			if (slow.data != stack.pop()) {
				return false;
			}
			slow = slow.next;
		}
		return true;
	}

	// uses stack
	public boolean isPalindrome(LLN head) {
		Stack<Integer> stack = new Stack<>();
		int len = 0;
		LLN curr = head;
		while (curr != null) {
			stack.push(curr.data);
			curr = curr.next;
			len++;
		}

		int limit = len % 2 == 0 ? len / 2 : len / 2 + 1;
		curr = head;
		for (int i = 0; i < limit; i++) {
			if (stack.pop() != curr.data) {
				return false;
			}
			curr = curr.next;
		}
		return true;
	}

	// uses recursion
	public LLN sumLists_1(LLN node1, LLN node2) {
		LLN prev = new LLN(0, null); // data,next will be updated by sumListsHelper
		sumListsHelper(node1, node2, false, prev);
		return prev.next;
	}

	private void sumListsHelper(LLN node1, LLN node2, boolean carry, LLN prev) {
		if (node1 == null && node2 == null && !carry) {
			return;
		}
		int d1 = node1 == null ? 0 : node1.data;
		int d2 = node2 == null ? 0 : node2.data;
		int sum = carry ? d1 + d2 + 1 : d1 + d2;
		if (sum > 9) {
			carry = true;
			sum -= 10;
		} else {
			carry = false;
		}
		LLN node = new LLN(sum, null);
		prev.next = node;

		// recursion
		node1 = node1 == null ? null : node1.next;
		node2 = node2 == null ? null : node2.next;
		sumListsHelper(node1, node2, carry, node);
	}

	public LLN sumListsForward(LLN node1, LLN node2) {
		Stack<Integer> s1 = new Stack<>();
		while (node1 != null) {
			s1.push(node1.data);
			node1 = node1.next;
		}
		Stack<Integer> s2 = new Stack<>();
		while (node2 != null) {
			s2.push(node2.data);
			node2 = node2.next;
		}

		boolean carry = false;
		int d1, d2, sum;
		LLN next = null;
		while (!s1.empty() || !s2.empty() || carry) {
			d1 = s1.empty() ? 0 : s1.pop();
			d2 = s2.empty() ? 0 : s2.pop();
			sum = carry ? d1 + d2 + 1 : d1 + d2;
			if (sum > 9) {
				carry = true;
				sum -= 10;
			} else {
				carry = false;
			}
			LLN node = new LLN(sum, next);
			next = node;
		}
		return next;

	}

	public LLN sumLists(LLN node1, LLN node2) {
		boolean carry = false;
		int d1, d2, sum;
		LLN head = null;
		LLN prev = null;
		while (node1 != null || node2 != null || carry) {
			d1 = node1 != null ? node1.data : 0;
			d2 = node2 != null ? node2.data : 0;
			sum = carry ? d1 + d2 + 1 : d1 + d2;
			if (sum > 9) {
				carry = true;
				sum -= 10;
			} else {
				carry = false;
			}
			LLN node = new LLN(sum, null);

			if (head == null) {
				head = node;
				prev = node;
			} else {
				prev.next = node;
			}
			prev = node;

			if (node1 != null) {
				node1 = node1.next;
			}
			if (node2 != null) {
				node2 = node2.next;
			}
		}
		return head;
	}

	public LLN partition(LLN node, int x) {
		LLN head = node;
		LLN tail = node;

		while (node != null) {
			LLN next = node.next;
			if (node.data < x) {
				node.next = head;
				head = node;
			} else {
				tail.next = node;
				tail = node;
			}
			node = next;
		}
		tail.next = null;
		return head;
	}

//	public LLN partition(LLN node, int x) {
//		LLN headPart1 = null;
//		LLN headPart2 = null;
//		LLN part1 = null;
//		LLN part2 = null;
//
//		while (node != null) {
//			LLN next = node.next;
//			node.next = null;
//			if (node.data < x) {
//				if (part1 == null) {
//					headPart1 = node;
//					part1 = node;
//				} else {
//					part1.next = node;
//					part1 = part1.next;
//				}
//			} else {
//				if (part2 == null) {
//					headPart2 = node;
//					part2 = node;
//				} else {
//					part2.next = node;
//					part2 = part2.next;
//				}
//			}
//			node = next;
//		}
//
//		if (headPart1 == null) {
//			return headPart2;
//		}
//		part1.next = headPart2;
//		return headPart1;
//	}

//	public LLN partition(LLN node, int x) {
//		LLN beforeStart = null; 
//		LLN beforeEnd = null; 
//		LLN afterStart = null; 
//		LLN afterEnd = null; 
//		
//		while (node != null) {
//			LLN next = node.next; 
//			node.next = null; 
//			if (node.data < x) {
//				if (beforeStart == null) {
//					beforeStart = node; 
//					beforeEnd = beforeStart; 
//				} else {
//					beforeEnd.next = node; 
//					beforeEnd = node; 
//				}
//			} else {
//				if (afterStart == null) {
//					afterStart = node; 
//					afterEnd = afterStart; 
//				} else {
//					afterEnd.next = node; 
//					afterEnd = node; 
//				}
//			} 
//			node = next; 
//		}
//		if (beforeStart == null) {
//			return afterStart; 
//		}
//		
//		beforeEnd.next = afterStart; 
//		return beforeStart; 
//	}

	public void deleteMiddleNode(LinkedListNode<T> head) {
		LinkedListNode<T> n1 = head;
		LinkedListNode<T> n2 = head;

		while (n2.next != null && n2.next.next != null) {
			if (n2.next.next.next == null || n2.next.next.next.next == null) {
				n1.next = n1.next.next;
			}
			n1 = n1.next;
			n2 = n2.next.next;
		}
	}

	public LinkedListNode<T> returnKthToLast(LinkedListNode<T> head, int k) {
		int posHead = posFromLast(head); // position of head from last
		if (posHead < k) {
			throw new IllegalArgumentException("Too big k");
		}

		LinkedListNode<T> curr = head;
		for (int i = 0; i < posHead - k; i++) {
			curr = curr.next;
		}
		return curr;
	}

	// returns position of node from the last (null) node
	// the last node returns 0, second last returns 1 and so on.
	private int posFromLast(LinkedListNode<T> node) {
		if (node == null) {
			return 0;
		}
		return posFromLast(node.next) + 1;
	}

	// Space: O(1)
	// Time : O(n^2)
	public void removeDuplicates1(LinkedListNode<T> head) {
		LinkedListNode<T> curr = head;
		while (curr != null) {
			LinkedListNode<T> running = curr;
			while (running != null && running.next != null) {
				if (running.next.data.equals(curr.data)) {
					running.next = running.next.next;
				}
				running = running.next;
			}
			curr = curr.next;
		}
	}

	// uses extra space to track "seen" nodes
	// Space: O(n)
	// Time : O(n)
	public void removeDuplicates(LinkedListNode<T> head) {
		if (head == null) {
			return;
		}
		java.util.LinkedList<T> seen = new java.util.LinkedList<>();
		seen.add(head.data);

		LinkedListNode<T> curr = head;
		while (curr != null) {
			LinkedListNode<T> next = curr.next;
			while (next != null && seen.contains(next.data)) {
				next = next.next;
			}
			if (next != null) {
				seen.add(next.data);
			}
			curr.next = next;
			curr = next;
		}
	}

}
