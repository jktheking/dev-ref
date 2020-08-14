package edu.jktheking.concurrent.casual;


public class PrintSequential {

	
	public static void main(String[] args) throws InterruptedException {
		
	
		
		for(int i=0;i<15;i++) {
	      
			Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				
			}
		}, "waiter-"+i);
			
			t.start();
			t.join();

			
		}
		
	}



}
