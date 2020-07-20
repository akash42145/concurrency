package com.example.threadfactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainWithExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * Create a Factory
		 */
		MyThreadFactory myFactory=new MyThreadFactory("MyThreadFactory");
	
		/*
		 * Crate a Task
		 */
		MyTask task=new MyTask();
		
		ExecutorService exe = Executors.newCachedThreadPool(myFactory);
		exe.submit(task);
		exe.shutdown();
		exe.awaitTermination(1, TimeUnit.SECONDS);
		
		/*
		 * Write the thread info to the console
		 */
		System.out.printf("Main: Thread information.\n");
		//System.out.printf("%s\n",thread2);
		System.out.printf("Main: End of the example.\n");

	}

}