// implements queue using 2 stacks 

package ctci.stacksandqueues;

import ctci.datastructures.S;

public class QueueFromStacks {
	private S stackNewest; // stores newest elements towards the head of stack
	private S stackOldest; // stores oldest elements towared the head of stack

	private void shiftOldestToNewest() throws Exception {
		// move all elements from stackOldest to stackNewest
		if (this.stackNewest.isEmpty() && !this.stackOldest.isEmpty()) {
			while (!stackOldest.isEmpty()) {
				this.stackNewest.push(this.stackOldest.pop());
			}
		}
	}

	private void shiftNewestToOldest() throws Exception {
		// move all elements from stackNewest to stackOldest
		if (this.stackOldest.isEmpty() && !this.stackNewest.isEmpty()) {
			while (!stackOldest.isEmpty()) {
				this.stackOldest.push(this.stackNewest.pop());
			}
		}
	}

	public void enqueue(int i) throws Exception {
		this.shiftOldestToNewest();
		this.stackNewest.push(i);
	}

	public int dequeue() throws Exception {
		this.shiftNewestToOldest();
		return this.stackOldest.pop();
	}

	public int peek() throws Exception {
		this.shiftNewestToOldest();
		return this.stackOldest.peek(); 
	}
	
	public boolean isEmpty() {
		return this.stackNewest.isEmpty() && this.stackOldest.isEmpty(); 
	}
}
