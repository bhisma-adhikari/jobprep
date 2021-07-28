package ctci.threadsandlocks;

public class SynchronizedBlock {
	public static void main(String[] args) {
		MyObj mo1 = new MyObj();
		MyObj mo2 = new MyObj();
		MyThread t1 = new MyThread(mo1, "1");
		MyThread t2 = new MyThread(mo1, "2");
		t1.start();
		t2.start();
	}

}

class MyThread extends Thread {
	private MyObj myObj;
	private String name;

	public MyThread(MyObj myObj, String name) {
		this.myObj = myObj;
		this.name = name;
	}

	@Override
	public void run() {
		this.myObj.foo(this.name);
	}
}

class MyObj {
	public void foo(String threadName) {
		synchronized (this) {
			System.out.println("Thread " + threadName + " starting");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread " + threadName + " interrupted");
			}
			System.out.println("Thread " + threadName + " ending");
		}

	}
}