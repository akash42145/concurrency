package com.example.threadfactory;

public class Main {

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
		
		/*
		 * Create a Thread using the Factory to execute the Task
		 */
		Thread thread=myFactory.newThread(task);
		Thread thread2=myFactory.newThread(task);
		
		/*
		 * Start the Thread
		 */
		thread.start();
		thread2.start();
		
		/*
		 * Wait for the finalization of the Thread
		 */
		thread.join();
		thread2.join();
		
		/*
		 * Write the thread info to the console
		 */
		System.out.printf("Main: Thread information.\n");
		System.out.printf("%s\n",thread2);
		System.out.printf("Main: End of the example.\n");

	}

}