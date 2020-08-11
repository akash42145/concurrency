package com.example.explicitlock;

public class ThreadInsideStaticBlock {
	
	static {
		Thread th = new Thread(() -> {
			System.out.println("Inside thread th.."+ Thread.currentThread().getName());
			greet();
		});
		System.out.println("Starting th thread..." +Thread.currentThread().getName());
		th.start();
		try {
			//th.join();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println("Done..." +Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		
		System.out.println("Inside main ::"+Thread.currentThread().getName());
		
	}
	private static void greet() {
		System.out.println("Hello from greet...");
		
	}

}
