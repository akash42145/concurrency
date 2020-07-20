package com.example.atomics;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.LongAdder;

public class AddUsingLongAdder {

	public static void main(String[] args) {

		LongAdder adder = new LongAdder();
		CyclicBarrier barrier = new CyclicBarrier(4);
		long start = System.currentTimeMillis();

		Thread thread1 = new Thread(() -> {
			for (int i1 = 0; i1 <= 10000; i1++) {
				adder.add(i1);
				System.out.println(Thread.currentThread().getName() +" adding " + i1);
			}
			await(barrier);
		});

		Thread thread2 = new Thread(() -> {
			for (int i1 = 0; i1 <= 10000; i1++) {
				adder.add(i1);
				System.out.println(Thread.currentThread().getName()+ " adding " + i1);
			}
			await(barrier);
		});
		
		Thread thread3 = new Thread(() -> {
			for (int i1 = 0; i1 <= 10000; i1++) {
				adder.add(i1);
				System.out.println(Thread.currentThread().getName()+ " adding " + i1);
			}
			await(barrier);
		});
		
		thread1.start();
		thread2.start();
		thread3.start();
		await(barrier);
		System.out.println("The sum is: " + adder.sum() +" in time "+ ( ( System.currentTimeMillis() - start)  ) );
	}

	
	private static void await(CyclicBarrier barrier) {
		try {
			System.out.println(Thread.currentThread().getName()+" is waiting to open barrier.");
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}
}
