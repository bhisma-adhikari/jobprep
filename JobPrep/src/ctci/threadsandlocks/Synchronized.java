package ctci.threadsandlocks;

public class Synchronized {
	public static void main(String[] main) {
		MyObject o1 = new MyObject(); 
		MyObject o2 = new MyObject(); 
		
		MyClass thread1 = new MyClass("1", o1); 
		MyClass thread2 = new MyClass("2", o1); 
		
		thread1.start();
		thread2.start(); 
		
		
	}
}

class MyClass extends Thread {
	String name; 
	MyObject myObject; 
	
	public MyClass(String name, MyObject myObject) {
		this.name = name; 
		this.myObject = myObject; 
	}
	
	@Override 
	public void run() {
		this.myObject.foo(this.name); 
	}
}

class MyObject{
	public synchronized void foo(String name) {
		try {
			System.out.println("Thread " + name + " starting.");
			Thread.sleep(1000);
			System.out.println("Thread " + name + " ending.");
		} catch (InterruptedException e) {
			System.out.println("Thread " + name + " interrupted."); 
		}
	}
}
