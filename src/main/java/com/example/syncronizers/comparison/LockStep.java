package com.example.syncronizers.comparison;

import java.util.concurrent.ThreadLocalRandom;

public class LockStep {

	protected final static int TASKS_PER_BATCH = 3;
	protected final static int BATCHES = 5;

	protected final void doTask(String batch) {
		System.out.printf("Task start %s%n", batch);
		//any value between 500 to 3000 (500 ms to 3 second)
		int ms = ThreadLocalRandom.current().nextInt(500, 3000);
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.printf("Task in batch %s took %dms%n", batch, ms);
	}
}
