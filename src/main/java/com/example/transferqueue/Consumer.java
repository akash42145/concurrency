package com.example.transferqueue;

/**
 *	This class implements the Consumer of the events. There is only
 * one consumer in the example that consumes 1002 events 
 *
 */
public class Consumer implements Runnable {

	
	private final MyPriorityTransferQueue<Event> buffer;
	
	
	public Consumer(MyPriorityTransferQueue<Event> buffer) {
		this.buffer=buffer;
	}
	
	
	@Override
	public void run() {
		for (int i=0; i<8; i++) {
			try {
				Event value=buffer.take();
				System.out.printf("Consumer: %s: Priority: %d\n",Thread.currentThread().getName(),value.getPriority());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}