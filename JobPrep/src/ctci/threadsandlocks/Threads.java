package ctci.threadsandlocks;

import utils.Utils;

public class Threads {
	public static void main(String[] args) {
		int[] numbers = new int[100];
		int delay = 2;

		Set1 set1 = new Set1(numbers, delay);
		Thread thread1 = new Thread(set1);

		Set2 set2 = new Set2(numbers, delay);
		Thread thread2 = new Thread(set2);

		thread1.start();
		thread2.start();
		
		while (thread1.isAlive() || thread2.isAlive()) {}

		Utils.printIntArray(numbers);

	}
}

class Set1 implements Runnable {
	int[] numbers;
	int delay;

	public Set1(int[] numbers, int delay) {
		this.numbers = numbers;
		this.delay = delay;
	}

	public synchronized void run() {
		for (int i = 0; i < this.numbers.length; i++) {
//			System.out.println("Set1");
			numbers[i] = 1;
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Set2 implements Runnable {
	int[] numbers;
	int delay;

	public Set2(int[] numbers, int delay) {
		this.numbers = numbers;
		this.delay = delay;
	}

	public synchronized void run() {
		for (int i = 0; i < this.numbers.length; i++) {
//			System.out.println("Set2");
			numbers[i] = 2;
			try {
				Thread.sleep(this.delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
