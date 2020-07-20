package com.example.forkjoin.customthread;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

public abstract class MyForkJoinTask extends ForkJoinTask<Void> {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public MyForkJoinTask(String name) {
		this .name=name;
	}
	
	protected abstract void compute();

	@Override
	public Void getRawResult() {
		return null;
	}

	@Override
	protected void setRawResult(Void value) {
		
	}

	@Override
	protected boolean exec() {
		Date start = new Date();
		compute();
		Date finish = new Date();
		long diff = finish.getTime() - start.getTime();
		System.out.printf("MyForkJoinTask : %s : %d Millisecond to complete\n", name, diff);
		
		return true;
	}
	
	public String getName() {
		return name;
	}

}
