// stack 

//AVAILABLE METHODS: 
//	- push()
//	- pop()
//	- peek() 
//	- isEmpty()

package ctci.datastructures;

import java.util.StringJoiner;


public class S {
	// stack node 
	private class SN {
		public int data; 
		public SN prev; 
		
		private SN(int data, SN prev) {
			this.data = data; 
			this.prev = prev; 
		}
		
		@Override 
		public String toString() {
			return this.data + ""; 
		}
	}

	
	private SN head;
	private int size; 
	
	public S () {
		this.head = null; 
		this.size = 0; 
	}
	
	
	@Override 
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		SN node = this.head; 
		while (node != null) {
			sj.add(node.toString()); 
			node = node.prev;
		}
		return sj.toString(); 
	}
	
	public void push(int i) {
		SN node = new SN(i, this.head); 
		this.head = node; 
		this.size++; 
	}
	
	public int pop() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty stack");
		}
		int i = this.head.data; 
		this.head = this.head.prev; 
		this.size--; 
		return i; 
	}
	
	public int peek() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty stack");
		}
		return this.head.data; 
	}
	
	public boolean isEmpty() {
		return this.size == 0; 
	}
}
