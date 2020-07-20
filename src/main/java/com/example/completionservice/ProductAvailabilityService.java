package com.example.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductAvailabilityService {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletionService<String> cs = new ExecutorCompletionService<String>(executor);

		AtomicInteger count = new AtomicInteger(1);
		List<Future<String>> futures = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			futures.add(cs.submit(new ProductAvailabilityFindTask(count)));
		}

		try {
			for (int i = 0; i < 6; i++) {
				String result = cs.take().get();
				System.out.println(result);				
				break;
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		finally {
			futures.forEach(f -> f.cancel(true));
		}

		executor.shutdown();

	}

}
