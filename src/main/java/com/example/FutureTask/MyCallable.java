package com.example.FutureTask;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("Inside call ");
		Thread.sleep(200);
		return 10;
	}

}
