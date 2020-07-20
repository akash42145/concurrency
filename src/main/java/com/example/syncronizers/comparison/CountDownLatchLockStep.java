package com.example.syncronizers.comparison;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchLockStep extends LockStep {

	public static void main(String[] args) {
		
		CountDownLatchLockStep lse = new CountDownLatchLockStep();
		ExecutorService pool = Executors.newFixedThreadPool(TASKS_PER_BATCH);
		for (int batch = 0; batch < BATCHES; batch++) {
			// We need a new CountDownLatch per batch, since they
			// cannot be reset to their initial value
			CountDownLatch latch = new CountDownLatch(TASKS_PER_BATCH);
			for (int i = 0; i < TASKS_PER_BATCH; i++) {
				int batchNo = batch +1;
				int taskNo = i+1;
				String batchTask = "Batch-"+batchNo +"=> Task-" + taskNo;
				pool.submit(() -> lse.task(latch, batchTask));
			}
		}
		pool.shutdown();
	}

	public void task(CountDownLatch latch, String batch) {
		latch.countDown();
		boolean interrupted = Thread.interrupted();
		while (true) {
			try {
				latch.await();
				break;
			} catch (InterruptedException e) {
				interrupted = true;
			}
		}
		if (interrupted)
			Thread.currentThread().interrupt();
		doTask(batch);
	}

}
