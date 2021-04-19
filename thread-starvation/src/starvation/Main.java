package starvation;

public class Main {
	
	private static Object lock = new Object();
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Worker("<t1> "), "Priority 10");
		Thread t2 = new Thread(new Worker("<t2> "), "Priority 10");
		Thread t3 = new Thread(new Worker("<t3> "), "Priority 10");
		Thread t4 = new Thread(new Worker("<t4> "), "Priority 10");
		Thread t5 = new Thread(new Worker("<t5> "), "Priority 10");
		
		t1.setPriority(10);
		t2.setPriority(8);
		t3.setPriority(6);
		t4.setPriority(4);
		t5.setPriority(2);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}
	
	private static class Worker implements Runnable {
		private int runCount = 1;
		private String pre;
		
		public Worker(String pre) {
			this.pre = pre;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0 ; i < 100 ; i ++) {
				synchronized(lock) {
					System.out.println(pre + runCount++);
				}
			}
		}
	}
}
