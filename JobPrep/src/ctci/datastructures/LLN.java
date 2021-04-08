package ctci.datastructures;

public class LLN {
	public int data; 
	public LLN next; 
	
	public LLN(int data, LLN next) {
		this.data = data; 
		this.next = next; 
	}
	
	@Override 
	public String toString() {
		return this.data + ""; 
	}
}
