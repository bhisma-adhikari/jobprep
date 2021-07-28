package ctci.threadsandlocks.diningphilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	private Lock lock; 
	
	public Chopstick() {
		this.lock = new ReentrantLock(); 
	}
	
	public void pickUp() {
		this.lock.lock();
	}
	
	public void putDown() {
		this.lock.unlock(); 
	}
}
