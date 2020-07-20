package com.example.transferqueue;

import java.util.concurrent.TimeUnit;

/**
 * Main class of the example.
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		
		MyPriorityTransferQueue<Event> buffer=new MyPriorityTransferQueue<>();		
		
		Producer producer=new Producer(buffer);
		
		Thread producerThreads[]=new Thread[2];
		for (int i=0; i<producerThreads.length; i++) {
			producerThreads[i]=new Thread(producer, "Thread-P"+i);
			producerThreads[i].start();
		}

		Consumer consumer=new Consumer(buffer);
		Thread consumerThread=new Thread(consumer, "Thread-C-0");
		consumerThread.start();

		for (int i=0; i<producerThreads.length; i++) {
			producerThreads[i].join();
		}
		
		System.out.printf("Main: Buffer: Consumer count: %d\n",buffer.getWaitingConsumerCount());

		
		Event myEvent=new Event("Core Event",0);
		buffer.transfer(myEvent);
		System.out.printf("Main: My Event has ben transfered.\n");
		
		
		
		TimeUnit.SECONDS.sleep(1);
		
		
		System.out.printf("Main: Buffer: Consumer count: %d\n",buffer.getWaitingConsumerCount());
		
		myEvent=new Event("Core Event 2",0);
		buffer.transfer(myEvent);
		
		System.out.printf("Main: Buffer: Consumer count: %d\n",buffer.getWaitingConsumerCount());
		
		myEvent=new Event("Core Event 3",10);
		buffer.tryTransfer(myEvent, 1, TimeUnit.SECONDS);
		
		consumerThread.join();
		
		System.out.printf("Main: End of the program\n");
	}

}
