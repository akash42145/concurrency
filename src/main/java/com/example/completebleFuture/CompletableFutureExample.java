package com.example.completebleFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

	public static void main(String[] args) throws InterruptedException {
		
		CompletableFutureExample cfe = new CompletableFutureExample();
		
		int result = cfe.getResultFromMultipleService();
		
		System.out.println("Result :"+result);

	}
	
	
	
	private int getResultFromMultipleService() throws InterruptedException {
		
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("Start executing 1st Service");
			calculation(4);
			return 4;
		}).thenApplyAsync(r -> {
			System.out.println("Start executing 2nd Service with 1st service result " + r);
			calculation(5);
			return r + 5;
		}).thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> {
			System.out.println("Start executing 3rd Service with previous result "+ s);
			calculation(3);
			return 3 + s;
		}));
		
		cf.thenRun(() -> System.out.println("Logger......................!!!"));
		
		CompletableFuture<Integer> finalCf = cf.thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> {
			System.out.println("Start executing 4rd Service with previous result "+ s);
			calculation(3);
			return 3 + s;
		}));
		
		System.out.println("NOT EXECUTED YET");
		Thread.sleep(1000);
		
		return finalCf.join();
		
	}



	private void calculation(int delay) {
		
		try {
			Thread.sleep(delay * 1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

}
