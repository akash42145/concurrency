package com.example.completionservice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductAvailabilityFindTask implements Callable<String> {
	
	private AtomicInteger shopNo;
	
	public ProductAvailabilityFindTask(AtomicInteger shopNo) {
		
		this.shopNo = shopNo;
		
	}

	@Override
	public String call() throws Exception {
		Random random = new Random();
		int delay = 1000 + random.nextInt(10000);
		Thread.sleep(delay);
		return "Product found in shop-"+shopNo + " in " + delay +" ms";
	}

}
