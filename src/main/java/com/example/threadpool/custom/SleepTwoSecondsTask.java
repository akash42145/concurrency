package com.example.threadpool.custom;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SleepTwoSecondsTask implements Callable<String> {

	/**
	 * Main method of the tasks. It only sleeps the current thread for two seconds
	 */
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		return new Date().toString();
	}

}
