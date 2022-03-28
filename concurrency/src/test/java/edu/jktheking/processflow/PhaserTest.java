package edu.jktheking.processflow;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.jktheking.concurrent.phaser.TaskPhaseRunnable;

public class PhaserTest {

	private ExecutorService executorService;
	private Phaser phaser;

	@BeforeAll
	public void configure() {

		executorService = Executors.newFixedThreadPool(10);
		// creates the phaser with this thread as registered by passing 1 as unarrived
		// party
		phaser = new Phaser(1);

	}

	/**
	 * This test will execute the program in two phases. 4 threads will execute the
	 * First phase(A) and 2 threads will execute the Second phase(B).
	 * @throws InterruptedException 
	 */
	@Test
	public void twoPhaseExceution() throws InterruptedException {

		assertEquals("starting the program so phase number should be 0", 0, phaser.getPhase());

		executorService.execute(new TaskPhaseRunnable("PHASE-A-TASK-1", phaser));
		executorService.execute(new TaskPhaseRunnable("PHASE-A-TASK-2", phaser));
		executorService.execute(new TaskPhaseRunnable("PHASE-A-TASK-3", phaser));
		executorService.execute(new TaskPhaseRunnable("PHASE-A-TASK-4", phaser));
		
		Thread.sleep(5L);
		// When the number of arrived parties becomes equal to the number of registered
		// parties, phaser allows the execution of registered thread.
		phaser.arriveAndAwaitAdvance();

		System.out.println("phase A of the program should be triggred, so phase number should now be 1");
		assertEquals("phase A of the program should be triggred, so phase number should now be 1", 1, phaser.getPhase());
		
		
		
		Thread.sleep(1000L);
		//starting phase 2 of the execution
		executorService.execute(new TaskPhaseRunnable("PHASE-B-TASK-1", phaser));
		executorService.execute(new TaskPhaseRunnable("PHASE-B-TASK-2", phaser));
		
		Thread.sleep(5L);
		phaser.arriveAndAwaitAdvance();
		System.out.println("phase B of the program should be triggred, so phase number should now be 2");
		assertEquals("phase B of the program should be triggred, so phase number should now be 2", 2, phaser.getPhase());
		
		
		
		Thread.sleep(1000L);
		//killing the program, now phase number must be zero
		phaser.arriveAndDeregister();
		System.out.println("termination status:"+ phaser.isTerminated() + " phase number:"+phaser.getPhase());

		
		
		

	}

	private void assertEquals(String string, int actual, int phase) {
		// TODO Auto-generated method stub
		
	}

}
