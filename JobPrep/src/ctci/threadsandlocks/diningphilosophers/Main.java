package ctci.threadsandlocks.diningphilosophers;

import java.util.*; 

public class Main {
	public static void main(String[] args) {
		Chopstick chopstick1 = new Chopstick(); 
		Chopstick chopstick2 = new Chopstick(); 
		Chopstick chopstick3 = new Chopstick(); 
		Chopstick chopstick4 = new Chopstick(); 
		
		Philosopher philosopher1 = new Philosopher("Phil1", chopstick4, chopstick1); 
		Philosopher philosopher2 = new Philosopher("Phil2", chopstick1, chopstick2); 
		Philosopher philosopher3 = new Philosopher("Phil3", chopstick2, chopstick3);
		Philosopher philosopher4 = new Philosopher("Phil4", chopstick3, chopstick4); 
		
		philosopher1.start();
		philosopher2.start(); 
		philosopher3.start();
		philosopher4.start(); 
		
		
		
	}
}
