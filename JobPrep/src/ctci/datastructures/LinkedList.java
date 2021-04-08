package ctci.datastructures;

import java.util.StringJoiner;

public class LinkedList<T> {
	public LinkedListNode<T> head; 
	
	public LinkedList(LinkedListNode<T> head) {
		this.head = head; 
	}
	
	public LinkedList(T[] elements) {
		LinkedListNode<T> next = null;
		for (int i = elements.length-1; i >=0; i--) {
			LinkedListNode<T> node = new LinkedListNode<>(elements[i], next); 
			next = node; 
			if (i == 0) {
				this.head = node;
			}
		}
	}
	
	@Override 
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		LinkedListNode<T> node = this.head; 
		while (node != null) {
			sj.add(node.data.toString()); 
			node = node.next;
		}
		return sj.toString(); 
	}
}
