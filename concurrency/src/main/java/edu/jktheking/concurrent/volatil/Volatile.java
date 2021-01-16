package edu.jktheking.concurrent.volatil;

import java.util.stream.IntStream;

/**
 * {@link https://www.javamex.com/tutorials/synchronization_volatile.shtml}
 * {@link http://tutorials.jenkov.com/java-concurrency/volatile.html}
 * 
 * 
 * <h5>In Shared Multiprocessor Architecture:</h5> Processors are responsible
 * for executing program instructions. Therefore, they need to retrieve both
 * program instructions and required data from RAM. Fetching from RAM is not
 * that ideal for them. To improve this situation, processors are using
 * strategies like(<b>A good explanation can be found on wiki against each
 * bullet</b>)
 * <ol>
 * <li><a href="https://en.wikipedia.org/wiki/Out-of-order_execution">Out of
 * Order Execution/instruction re-ordering</a></li>
 * <li>Branch Prediction</li>
 * <li>Speculative Execution</li>
 * <li>Caching</li>
 * </ol>
 * <b>*Java compiler and JVM also does re-ordering, which can also cause the
 * problem of race condition.. JVM re-ordering may be aligned with out of order
 * execution of underlying CPU</b>
 * 
 * <pre>
 Let's understand the "Happens-Before Ordering" phrase in the context of volatile variable using concept of
  'Out of Order Execution by CPU cores'
  
  In general CPU maintains private 
   1. Local cache and CPU registers registers.
   2. Instruction queue
   3. Result queue
  
  <b>Out-of-order processors</b> breaks up the processing of instructions into these steps:
	1. Instruction fetch.
	2. Instruction dispatch to an instruction queue (also called instruction buffer or reservation stations).
	3. The instruction waits in the queue until its input operands are available. The instruction is then allowed to leave the queue earlier than the older instructions whose operands are yet to be available.
	4. The instruction is issued to the appropriate functional unit and executed by that unit.
	5. The results are queued in result queue.
	6. Only after all older instructions have their results written back to the register, then this result is written back to the register. This is called the graduation or retire stage.
 * 
 *  Thus we have seen, though the instruction re-ordering happens still the program semantic remains same, because the results are written to the register in the order of execution.
 *  This is why, single threaded program does not see the side effects of instruction re-ordering. Instruction reordering has impact in multi-threaded environment where
 *   order-of-execution of one thread for the 'same instructions' is different than the order-of-execution of other thread.
 *   
 *  For multithreaded applications, we need to ensure a couple of rules for consistent behavior:
 *  -----------------------------------------------------------------------------------------------
   1. Mutual Exclusion – only one thread executes a critical section at a time
   2. Visibility – changes made by one thread to the shared data are visible to other threads to maintain data consistency.
   3. In-order-execution
    <b>'synchronized' or 'lock' provide all of the above properties, at the cost of application performance.
    'Volatile' variables can help ensure the 'visibility' aspect and incomplete 'in-order-execution' without, of course, providing 'mutual-exclusion'.
     </b> 
   <b>
   The purpose of volatile is to ensure that when one thread writes a value to the field, the value written is "immediately available" to any thread that subsequently reads it. 
   Conversely, other threads reading the value are "forced" to take that immediately available value, rather than relying on a value previously read and cached(in register) by those other threads.
   So, it does not mean that cpu registers are not involved, cpu registers are involved for all kind of operations irrespective of the the variable being volatile or not.
   </b>   
   Let's understand the simple instruction operation:
   CASE A: non volatile operand based operation
    int x;
    x = x + 1;
    cpu will load the value of x into register if it is not already present in the register. perform the addition operation by 1 in the register.
    the result will be available in the register. It will on some later time flushed into the main memory not immediately.  
    ----------
     
    CASE B: non volatile operand based operation
    int volatile y;
    y = y+1;
    
     CPU will load the value of y into register from the main memory irrespective of whether the value is already present in the register or not.
     addition operation will happen in the register and now cpu register will hold the new value of y.
     But because being volatile 'memory barrier' instruction flushes the value of y from register to main-memory immediately. 
    
 *
 *
 * <I><b> Incomplete 'in-order-execution or Happens-Before Guarantee of volatile variables <b><I>
 * 
 * When a write of volatile variable is flushed from registers to the main memory, write to the other non-volatile variables defined before volatile variable are also flushed.
 * It means other threads reading the volatile variable also sees the correct value of other non-volatile variables but after the read of volatile variable.
 * 
 * Write rule: write of variables visible to a thread and defined before the write of a volatile variable 
 * happens before the write of volatile variable. i.e in-order-write-execution happens for the non-volatile variables which
 * are defined before the volatile-variable. But there is no guarantee for in-order-write-execution for variables defined after
 * volatile variable. Also there is no guarantee of in-order-execution among the non-volatile variables defined before and after the 
 * volatile variable. In-order-write-execution is only guaranteed wrt volatile-variable-write and set of non-volatile-variables-write define
 * before the  volatile write.  
 * 
 * Read rule: read of volatile variable happens before the read of non volatile variables if the volatile-variable-read is defined before the non-volatile-variable-read by the program.
 * That is in-order-execution happens for the case where volatile-variable-read is defined before non-volatile-variables read. But the same is not
 * guarantee among the non-volatile-reads  defined before and after the volatile-reads. 
 * 
 * <b>Note:Race condition guarantee by volatile variable</b>
 * * Race condition will not happen among multiple threads which are writing to the volatile variable if the operation is atomic.
 * eg. assigning a particular value to the volatile variable(e.g flag = true/ count=67)
 * 
 * Case of counter increment/or decrement:
 * x++; this is not the atomic operation:
 * x=x+1; 
 * instruction 1: x is read from the memory or register
 * instruction 2: 1 is added to the x
 * 
 * Because of happens before or partial in-order-execution, if increment happens by one thread and other threads will read there
 * will be no race condition.
 * But if multiple threads will try to increment then there will be the race condition mentioned below:
 * multiple threads can read the value of x from the main memory to register simultaneously, they will add the 1 to their respective copy
 * and then flush the result to main memory immediately, doing so will overwrite each others value.
 * 
 *;/
 *   
 *  
 *     
 * 
 * </pre>
 * 
 */
public class Volatile {
	
	private int counter = 0;
	private volatile boolean flag = false;
	
	
	private static final class Reader implements Runnable{
		private Volatile volatilee = null;
		private Reader(Volatile volatilee){
			this.volatilee = volatilee;
		}

		@Override
		public void run() {
			while(!volatilee.flag) {
				Thread.yield();
			}
			System.out.println(Thread.currentThread().getName()+":"+volatilee.counter);
			
		}
	}
    
	private static final class Writer implements Runnable{
		private Volatile volatilee = null;
		private Writer(Volatile volatilee){
			this.volatilee = volatilee;
		}

		@Override
		public void run() {
			/**
			 * in-order execution of set of(line1,line2) and line-volatile
			 * line1, line2 can be re-ordered among themselves. but cannot be 
			 * re-ordered wrt volatile variable 'flag'.
			 * */
			volatilee.counter++;//line1
			volatilee.counter++; //line2
	
		     volatilee.flag =true; //line-volatile
		      
			
		}
	}
	
	
	private static void nonRaceConditionedExecution() {
		Volatile volatilee = new Volatile();
		Thread writer = new Thread(new Writer(volatilee));
		writer.start();
		
		IntStream intstream = IntStream.range(0, 10);
		intstream.forEach(i->{
			new Thread(new Reader(volatilee), "non-raced-thread-"+i).start();
		});
	}
	

	private static void RaceConditionedExecution() {
		
		Volatile volatilee = new Volatile();
	
		IntStream intstreamwriter = IntStream.range(0, 10);
		intstreamwriter.forEach(i->{
			new Thread(new Writer(volatilee)).start();
		});
		
		IntStream intstreamReader = IntStream.range(0, 10);
		intstreamReader.forEach(i->{
			new Thread(new Reader(volatilee), "raced-thread-"+i).start();
		});
	}
	
	public static void main(String...strings) throws InterruptedException {
		nonRaceConditionedExecution();
		Thread.sleep(3000);
		System.out.println("----------- thread racing strats here----------------");
		RaceConditionedExecution();
		
		
	}
	
}
