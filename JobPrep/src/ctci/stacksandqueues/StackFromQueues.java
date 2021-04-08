// stack from 2 queues 

package ctci.stacksandqueues;

import ctci.datastructures.Q;

public class StackFromQueues {
	private Q q1;
	private Q q2;

	public StackFromQueues() {
		this.q1 = new Q();
		this.q2 = new Q();
	}

	private void transerAllButTail(Q from, Q to) throws Exception {
		// transfers all elements except tail element
		// assumptions:
		// - 'from' has at least 1 element
		while (from.size() > 1) {
			to.add(from.remove());
		}
	}

	public void push(int i) {
		// at least one of q1 and q2 is guaranteed to be empty
		Q nonEmpty = this.q1.isEmpty() ? this.q2 : this.q1;
		nonEmpty.add(i);
	}

	public int pop() throws Exception{
		Q from = this.q1.isEmpty() ? this.q2 : this.q1;
		Q to = this.q1.isEmpty() ? this.q1 : this.q2; 
		this.transerAllButTail(from, to);
		return from.remove(); 
	}
	
	public int peek() throws Exception {
		Q from = this.q1.isEmpty() ? this.q2 : this.q1;
		Q to = this.q1.isEmpty() ? this.q1 : this.q2; 
		this.transerAllButTail(from, to);
		int i = from.remove();
		to.add(i);
		return i; 
	}

}
