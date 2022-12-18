package com.example.stampedlock.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class ConvertToWriteLock {

	private final StampedLock lock = new StampedLock();
	private int balance = 1000;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(5);
		ConvertToWriteLock convertToWriteLock = new ConvertToWriteLock();
		
		for(int i=0 ; i <10; i++) {
			es.execute(() -> {
				convertToWriteLock.withdraw(50);
			});
		}	
		
		es.shutdown();
		es.awaitTermination(1, TimeUnit.SECONDS);
		
	}

	public void withdraw(int amount) {

		long stamp = lock.readLock();
		try {
			if (amount <= balance) {
				long convertStamp = lock.tryConvertToWriteLock(stamp);

				if (convertStamp != 0L) {
					System.out.println("Lock successfully converted ..."+ Thread.currentThread().getName());

					stamp = convertStamp;
					balance = balance - amount;

					System.out.println("New balance: " + balance);
					//break;
				} else {
					System.out.println("Read Lock not be converted to write lock , Now taking Exclusive write lock ..." +Thread.currentThread().getName());
					lock.unlockRead(stamp);
					stamp = lock.writeLock();
				}
			}
		} finally {
			lock.unlock(stamp);
		}
	}

}
