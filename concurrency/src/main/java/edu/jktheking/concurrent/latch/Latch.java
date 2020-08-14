package edu.jktheking.concurrent.latch;

import java.util.concurrent.CountDownLatch;

public class Latch {
	
	public static void main(String[] args) throws InterruptedException {
		
		int countToDown = 5;
		CountDownLatch cdLatch = new CountDownLatch(countToDown);
		
		for(int i=0;i<15;i++) {
	      new Thread(new Waiter(cdLatch), "waiter-"+i).start();

			
		}
		new Thread(new Operator(cdLatch, countToDown)).start();
	}

}


class Waiter implements Runnable{
	
	private CountDownLatch cdLatch;
	
	Waiter(CountDownLatch cdLatch){
		this.cdLatch = cdLatch;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" waiting for countDown to become 0");
		try {
			cdLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" executing post countDown");
		
	}
	
}



class Operator implements Runnable{
	
	private CountDownLatch cdLatch;
	private int countToDown;
	
	Operator(CountDownLatch cdLatch, int countToDown){
		this.cdLatch = cdLatch;
		this.countToDown = countToDown;
	}

	@Override
	public void run() {
		
		for(; countToDown >= 0; countToDown--) {
			System.out.println("current count down:"+countToDown);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cdLatch.countDown();
		}
		
	}
	
}


