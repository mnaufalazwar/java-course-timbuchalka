package consumerproducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class Main3 {

	public static void main(String[] args) {
		
		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		MyProducer3 myProducer = new MyProducer3(buffer, "!!");
		MyConsumer3 con1 = new MyConsumer3(buffer, "@");
		MyConsumer3 con2 = new MyConsumer3(buffer, "#");
		
		executorService.execute(myProducer);
		executorService.execute(con1);
		executorService.execute(con2);
		
		executorService.shutdown();
		
	}
}

class MyProducer3 implements Runnable{
	private ArrayBlockingQueue<String> buffer;
	private String pre;
	
	public MyProducer3(ArrayBlockingQueue<String> buffer, String pre) {
		this.buffer = buffer;
		this.pre = pre;
	}
	
	public void run() {
		Random random = new Random();
		String[] nums = {"1","2","3","4","5"};
		
		for(String num : nums) {
			try {
				System.out.println(pre + " Adding..." + num);
				buffer.put(num);
				Thread.sleep(random.nextInt(1500));
			}catch(InterruptedException e) {
				System.out.println("Producer was interrupted");
			}
		}
		
		System.out.println(pre + " Adding EOF end exiting...");
		
		try {
			buffer.put("EOF");
		}
		catch(InterruptedException e) {
			
		}
	}
}

class MyConsumer3 implements Runnable{
	private ArrayBlockingQueue<String> buffer;
	private String pre;
	
	public MyConsumer3(ArrayBlockingQueue<String> buffer, String pre) {
		this.buffer = buffer;
		this.pre = pre;
	}
	
	public void run() {
		
		while(true) {
			synchronized(buffer) {
				try {
					if(buffer.isEmpty()) {
						continue;
					}
						
					if(buffer.peek().equals("EOF")) {
						System.out.println(pre + " Exiting");
						break;
					}
					else {
						System.out.println(pre + " Removed " + buffer.take());
					}
				}
				catch(InterruptedException e) {
				}
			}
		}
	}
}