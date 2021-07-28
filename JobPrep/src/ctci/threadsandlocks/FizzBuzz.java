package ctci.threadsandlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz extends Thread {
	private int max;
	private boolean divBy3;
	private boolean divBy5;
//
	private static int counter = 0;
//	private Lock lock = new ReentrantLock();
	
	public FizzBuzz(int max, boolean divBy3, boolean divBy5) {
		this.max = max;
		this.divBy3 = divBy3;
		this.divBy5 = divBy5;
	}

//	@Override
//	public void run() {
//		for (int i = 0; i < this.max; i++) {
//			if (this.divBy3 && this.divBy5 && i % 3 == 0 && i % 5 == 0) {
//				System.out.println(this.counter + " FizzBuzz");
//			} else if (this.divBy3 && !this.divBy5 && i % 3 == 0 && i % 5 != 0) {
//				System.out.println(this.counter + " Fizz");
//			} else if (!this.divBy3 && this.divBy5 && i % 3 != 0 && i % 5 == 0) {
//				System.out.println(this.counter + " Buzz");
//			} else if (!this.divBy3 && !this.divBy5 && i % 3 != 0 && i % 5 != 0) {
//				System.out.println(this.counter);
//			}
//
//		}
//	}

	
	@Override
	public synchronized void run() {
		while (this.counter < this.max) {
			if (this.divBy3 && this.divBy5 && this.counter % 3 == 0 && this.counter % 5 == 0) {
				System.out.println(this.counter + " FizzBuzz");
			} else if (this.divBy3 && !this.divBy5 && this.counter % 3 == 0 && this.counter % 5 != 0) {
				System.out.println(this.counter + " Fizz");
			} else if (!this.divBy3 && this.divBy5 && this.counter % 3 != 0 && this.counter % 5 == 0) {
				System.out.println(this.counter + " Buzz");
			} else if (!this.divBy3 && !this.divBy5 && this.counter % 3 != 0 && this.counter % 5 != 0) {
				System.out.println(this.counter);
			}
			this.counter++; 

		}
	}

	public static void main(String[] args) {
		int max = 31;
		FizzBuzz threadFizzBuzz = new FizzBuzz(max, true, true);
		FizzBuzz threadFizz = new FizzBuzz(max, true, false);
		FizzBuzz threadBuzz = new FizzBuzz(max, false, true);
		FizzBuzz threadNum = new FizzBuzz(max, false, false);

		threadFizzBuzz.start();
		threadFizz.start();
		threadBuzz.start();
		threadNum.start();

	}

}