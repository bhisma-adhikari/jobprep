package ctci.threadsandlocks.diningphilosophers;


public class Philosopher extends Thread {
	private String name; 
	private int bites = 1; 
	private Chopstick left; 
	private Chopstick right; 
	
	public Philosopher(String name, Chopstick left, Chopstick right) {
		this.name = name; 
		this.left = left; 
		this.right = right; 
	}
	
	private void eat() {
		this.pickUp();
		this.chew();
		this.putDown();
	}
	
	private void pickUp() {
		this.left.pickUp();
		this.right.pickUp();
	}
	
	private void chew() {
		System.out.println(this.name + " chewing");
	}
	
	private void putDown() {
		this.right.putDown();
		this.left.putDown();
	}
	
	@Override 
	public void run() {
		for (int i = 0; i < this.bites; i++) {
			this.eat();
		}
	}
	
	
	
}
