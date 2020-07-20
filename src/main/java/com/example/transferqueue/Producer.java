package com.example.transferqueue;

import java.util.Random;

/**
 * This class implements the producers of data. It store 100
 * events in the queue with incremental priority
 *
 */
public class Producer implements Runnable {
	
	
	private final MyPriorityTransferQueue<Event> buffer;
	
	
	public Producer(MyPriorityTransferQueue<Event> buffer) {
		this.buffer=buffer;
	}	
	
	@Override
	public void run() {
		Random randdom = new Random();
		for (int i=0; i<3; i++) {
			int nextInt = randdom.nextInt(100);
			Event event=new Event(Thread.currentThread().getName(), nextInt);
			//System.out.printf("Producer: %s: Priority : %d\n", Thread.currentThread().getName(), nextInt);
			buffer.put(event);
		}
	}

}
