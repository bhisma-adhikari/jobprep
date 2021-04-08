package ctci.datastructures;

public class LinkedListNode<T> {
	public T data; 
	public LinkedListNode next; 
	
	public LinkedListNode(T data, LinkedListNode next) {
		this.data = data; 
		this.next = next; 
	}
	
	@Override 
	public String toString() {
		return this.data.toString(); 
	}
}
