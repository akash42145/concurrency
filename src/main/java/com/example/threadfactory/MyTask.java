package com.example.threadfactory;

import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable{
	
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Done running MyTask.." + Thread.currentThread().getName());
	}
}
