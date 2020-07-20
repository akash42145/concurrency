package com.example.producerConsumer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UsingLock {

	public static void main(String[] args) {
		
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		System.out.println(ForkJoinPool.commonPool());

		Worker worker = new Worker();

		Thread t1 = new Thread(() -> {
			try {
				worker.producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				worker.consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		});
		
		t1.start();
		t2.start();
	}

}

class Worker{
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void producer() throws InterruptedException {
		lock.lock();
		System.out.println("Producing task...");
		condition.await();
		System.out.println("Producing again...");
		lock.unlock();
		
	}
	
	public void consumer() throws InterruptedException {
		
		lock.lock();
		System.out.println("Consuming task...");
		condition.signal();
		Thread.sleep(1000);
		System.out.println("Consuming again...");
		lock.unlock();
		
	}
}
