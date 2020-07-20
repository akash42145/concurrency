package com.example.FutureTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

	public static void main(String[] args) {
		FutureTask<Void> ft = new FutureTask<Void>(new MyRunnable(), null);
		
		FutureTask<Integer> ftC =  new FutureTask<Integer>(new MyCallable());
		
		ft.run();
		ftC.run();
		
		System.out.println("---");
		//Thread.currentThread().interrupt();
		
		try {
			System.out.println(ftC.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

}
