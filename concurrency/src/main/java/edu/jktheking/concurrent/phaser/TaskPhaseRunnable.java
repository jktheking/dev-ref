package edu.jktheking.concurrent.phaser;

import java.util.concurrent.Phaser;

public class TaskPhaseRunnable implements Runnable {

	private final String threadName;
	private final Phaser phaser;

	public TaskPhaseRunnable(String threadName, Phaser phaser) {
		super();
		this.threadName = threadName;
		this.phaser = phaser;
		// registering this thread to phaser.
		phaser.register();
	}

	@Override
	public void run() {
		System.out.println(String.format("%s: before arriveAndAwait PHASE NUMBER:%s", threadName, phaser.getPhase()));
		// now thread will block on arriveAndAwaitAdvance, till the number of arrived
		// parties becomes equal to the number of registered
		phaser.arriveAndAwaitAdvance();
		System.out.println(String.format("%s: after arriveAndAwait PHASE NUMBER:%s", threadName, phaser.getPhase()));

		try {
			// System.out.println("starting long running task for thread:"+threadName);
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		phaser.arriveAndDeregister();

		System.out
		.println(String.format("%s: after arriveAndDeregister PHASE NUMBER:%s", threadName, phaser.getPhase()));

	}

}
