package consumerproducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		List<String> buffer = new ArrayList<>();
		
		MyProducer myProducer = new MyProducer(buffer, "!");
		MyConsumer con1 = new MyConsumer(buffer, "@");
		MyConsumer con2 = new MyConsumer(buffer, "#");
		
		(new Thread(myProducer)).start();
		(new Thread(con1)).start();
		(new Thread(con2)).start();
		
	}

}

class MyProducer implements Runnable{
	private List<String> buffer;
	private String pre;
	
	public MyProducer(List<String> buffer, String pre) {
		this.buffer = buffer;
		this.pre = pre;
	}
	
	public void run() {
		Random random = new Random();
		String[] nums = {"1","2","3","4","5"};
		
		for(String num : nums) {
			try {
				System.out.println(pre + " Adding..." + num);
				
				synchronized(buffer) {
					buffer.add(num);				
				}
				
				Thread.sleep(random.nextInt(1500));
			}catch(InterruptedException e) {
				System.out.println("Producer was interrupted");
			}
		}
		
		System.out.println(pre + " Adding EOF end exiting...");
		
		synchronized(buffer) {
			buffer.add("EOF");
		}
	}
}

class MyConsumer implements Runnable{
	private List<String> buffer;
	private String pre;
	
	public MyConsumer(List<String> buffer, String pre) {
		this.buffer = buffer;
		this.pre = pre;
	}
	
	public void run() {
		while(true) {
			
			synchronized(buffer) {
				if(buffer.isEmpty()) {
					continue;
				}
				if(buffer.get(0).equals("EOF")) {
					System.out.println(pre + " Exiting");
					break;
				}
				else {
					System.out.println(pre + " Removed " + buffer.remove(0));
				}
			}
			
		}
	}
}









