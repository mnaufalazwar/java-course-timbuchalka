package concurrency;

public class Main {
	
	public static void main(String[] args) {
		
//		System.out.println("Hello from main thread");
		
//		AnotherThread anotherThread = new AnotherThread();
//		anotherThread.start();
		
		Thread myRunnable = new Thread(new MyRunnable());
		myRunnable.setName("My Runnable");
		myRunnable.start();
		
		new Thread() {
			@Override
			public void run() {
				System.out.println("Hello from ANON thread");
				try {
					myRunnable.join(1000);
					System.out.println("join from anonymous");
				}
				catch(InterruptedException e) {
					System.out.println("terminated");
				}
				
			}
		}.start();
		
//		Thread myRunnable2 = new Thread(new MyRunnable() {
//
//			@Override
//			public void run() {
//				System.out.println("Hello from anonymous runnable thread");
//			}
//			
//		});
//		myRunnable2.start();
//		myRunnable.interrupt();
		
//		System.out.println("Hello again from main thread");
		
	}

}
