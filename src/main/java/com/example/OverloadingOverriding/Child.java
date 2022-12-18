package com.example.OverloadingOverriding;

public class Child extends Parent {
	
	private int i = calVal(5);
	
	
	{
		System.out.println("Child Initializing Block");
	}
	static {
		System.out.println("Child Static Block");
	}
	private static int j = calStatic();
	public Child() {
		this(10);
		System.out.println("Child :: DC "+i);
	}	

	public Child(int i) {
		this.i=this.i + i;
		System.out.println("Child :: AC "+i);
		
	}
	
	private static int calStatic() {
		System.out.println("Inside calStatic()");
		return 0;
	}

	
	private int calVal(int j) {
		System.out.println("Inside calVal()");
		return j * 3;
	}
	@Override
	public void call() {
		System.out.println("Child :: call()");
		super.call();
		
	}
	@Override
	public void print() {
		System.out.println("Child :: print()");
		
	}

	@Override
	public Java method()  {		
		return null;
	}

	@Override
	public Java operation() throws RuntimeException {		
		return null;
	}
	
	public static void staticMethod() {
		System.out.println("Child :: Inside staticMethod()");
		
	}

}
