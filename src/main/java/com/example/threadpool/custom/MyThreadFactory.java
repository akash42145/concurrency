package com.example.threadpool.custom;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;



public class MyThreadFactory implements ThreadFactory {
	
	private AtomicInteger threadNum = new AtomicInteger(1);	
	private String prefix;
	
	public MyThreadFactory(String poolName) {		
		this.prefix = poolName + "-thread-"; 
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, prefix+threadNum.getAndIncrement());
		return t;
	}

}
