package com.example.FutureTask;

import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		
		System.out.printf("Task: inside run \n");
		try {
			TimeUnit.SECONDS.sleep(2);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

}
