package ctci.datastructures;

import java.util.StringJoiner;

public class LL {
	public LLN head; 
	
	public LL(LLN head) {
		this.head = head; 
	}
	
	public LL(int[] elements) {
		LLN next = null;
		for (int i = elements.length-1; i >=0; i--) {
			LLN node = new LLN(elements[i], next); 
			next = node; 
			if (i == 0) {
				this.head = node;
			}
		}
	}
	
	@Override 
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		LLN node = this.head; 
		while (node != null) {
			sj.add(node.toString()); 
			node = node.next;
		}
		return sj.toString(); 
	}
}
