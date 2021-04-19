package concurrency;

public class MyRunnable implements Runnable{

	@Override
	public void run() {
		
		System.out.println("Hello from = " + Thread.currentThread().getName());
		
		try {
			Thread.sleep(3000);
		}
		catch(InterruptedException e) {
			System.out.println("another thread woke me up");
		}
		
		System.out.println("Hello from = " + Thread.currentThread().getName() + " Three seconds have passed");
		
	}

}
