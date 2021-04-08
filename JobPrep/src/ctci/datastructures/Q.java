// queue  

//AVAILABLE METHODS: 
//	- add()
//	- remove()
//	- peek() 
//	- isEmpty()
//  - size() 

package ctci.datastructures;

import java.util.StringJoiner;


public class Q {
	// queue node 
	private class QN {
		public int data; 
		public QN next; 
		
		private QN(int data, QN next) {
			this.data = data; 
			this.next = next; 
		}
		
		@Override 
		public String toString() {
			return this.data + ""; 
		}
	}

	
	private QN head;
	private QN tail; 
	private int size; 
	
	public Q () {
		this.head = null;
		this.tail = this.head; 
		this.size = 0; 
	}
	
	
	@Override 
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		QN node = this.head; 
		while (node != null) {
			sj.add(node.toString()); 
			node = node.next;
		}
		return sj.toString(); 
	}
	
	public void add(int i) {
		QN node = new QN(i, null);
		if (this.head == null) {  // tail is also null 
			this.head = node; 
		} else {
			this.tail.next = node; 
		}
		this.tail = node; 
		this.size++; 
	}
	
	public int remove() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty queue");
		}
		int i = this.head.data; 
		this.head = this.head.next; 
		this.size--; 
		return i; 
	}
	
	public int peek() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty queue");
		}
		return this.head.data; 
	}
	
	public boolean isEmpty() {
		return this.size == 0; 
	}
	
	public int size() {
		return this.size; 
	}
}
