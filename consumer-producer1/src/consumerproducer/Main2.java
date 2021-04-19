package consumerproducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class Main2 {
	
public static void main(String[] args) {
		
		List<String> buffer = new ArrayList<>();
		ReentrantLock bufferLock = new ReentrantLock();
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		MyProducer2 myProducer = new MyProducer2(buffer, "!", bufferLock);
		MyConsumer2 con1 = new MyConsumer2(buffer, "@", bufferLock);
		MyConsumer2 con2 = new MyConsumer2(buffer, "#", bufferLock);
		
//		(new Thread(myProducer)).start();
//		(new Thread(con1)).start();
//		(new Thread(con2)).start();
		
		executorService.execute(myProducer);
		executorService.execute(con1);
		executorService.execute(con2);
		
		//Run thread that return something
		Future<String> future = executorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("$ Writing from callable");
				return "return something";
			}
			
		});
		
		try {
			System.out.println(future.get());
		}
		catch(ExecutionException e) {
			
		}
		catch(InterruptedException e) {
			
		}
		
		executorService.shutdown();
		
	}

}

class MyProducer2 implements Runnable{
	private List<String> buffer;
	private String pre;
	private ReentrantLock bufferLock;
	
	public MyProducer2(List<String> buffer, String pre, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.pre = pre;
		this.bufferLock = bufferLock;
	}
	
	public void run() {
		Random random = new Random();
		String[] nums = {"1","2","3","4","5"};
		
		for(String num : nums) {
			try {
				System.out.println(pre + " Adding..." + num);
				
				bufferLock.lock();
				try {
					buffer.add(num);
				}
				finally {
					bufferLock.unlock();
				}
				
				Thread.sleep(random.nextInt(1500));
			}catch(InterruptedException e) {
				System.out.println("Producer was interrupted");
			}
		}
		
		System.out.println(pre + " Adding EOF end exiting...");
		
		bufferLock.lock();
		try {
			buffer.add("EOF");
		}
		finally {
			bufferLock.unlock();
		}
	}
}

class MyConsumer2 implements Runnable{
	private List<String> buffer;
	private String pre;
	private ReentrantLock bufferLock;
	
	public MyConsumer2(List<String> buffer, String pre, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.pre = pre;
		this.bufferLock = bufferLock;
	}
	
	public void run() {
		
		int counter = 0;
		while(true) {
			if(bufferLock.tryLock()) {
				try {
					if(buffer.isEmpty()) {
						continue;
					}
					
					System.out.println("counter = " + counter);
					counter = 0;
					
					if(buffer.get(0).equals("EOF")) {
						System.out.println(pre + " Exiting");
						break;
					}
					else {
						System.out.println(pre + " Removed " + buffer.remove(0));
					}
				}
				finally {
					bufferLock.unlock();
				}
			}
			else {
				counter++;
			}
		}
	}
}
