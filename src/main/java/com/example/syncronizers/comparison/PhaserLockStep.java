package com.example.syncronizers.comparison;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserLockStep extends LockStep {
	 public static void main(String... args) {
		 PhaserLockStep lse = new PhaserLockStep();
		    ExecutorService pool = Executors.newFixedThreadPool(TASKS_PER_BATCH);
		    Phaser phaser = new Phaser(TASKS_PER_BATCH);
		    for (int batch = 0; batch < BATCHES; batch++) {
		      for (int i = 0; i < TASKS_PER_BATCH; i++) {
		    	  int taskNo = i+1;
		        pool.submit(() -> lse.task(phaser, taskNo));
		      }
		    }
		    pool.shutdown();
		  }

		  public void task(Phaser phaser , int taskNo) {
		    phaser.arriveAndAwaitAdvance();
		    String batchTask = "Batch-"+phaser.getPhase() +"=> Task-" + taskNo;
		    doTask(batchTask);
		  }

}
