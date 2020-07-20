package com.example.syncronizers.comparison;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierLockStep extends LockStep {

	public static void main(String... args) {
		CyclicBarrierLockStep lse = new CyclicBarrierLockStep();
	    ExecutorService pool = Executors.newFixedThreadPool(TASKS_PER_BATCH);
	    CyclicBarrier barrier = new CyclicBarrier(TASKS_PER_BATCH);
	    for (int batch = 0; batch < BATCHES; batch++) {
	      for (int i = 0; i < TASKS_PER_BATCH; i++) {
	    	  int batchNo = batch +1;
				int taskNo = i+1;
				String batchTask = "Batch-"+batchNo +"=> Task-" + taskNo;
	        pool.submit(() -> lse.task(barrier, batchTask));
	      }
	    }
	    pool.shutdown();
	  }

	  public void task(CyclicBarrier barrier, String batch) {
	    boolean interrupted = Thread.interrupted();
	    while (true) {
	      try {
	        barrier.await();
	        break;
	      } catch (InterruptedException e) {
	        interrupted = true;
	      } catch (BrokenBarrierException e) {
	        throw new AssertionError(e);
	      }
	    }
	    if (interrupted) Thread.currentThread().interrupt();
	    doTask(batch);
	  }

}
