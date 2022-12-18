package com.example.OverloadingOverriding;

import java.io.FileNotFoundException;

public abstract class Parent {
	
	private static int i = parentStatic();
	
	{
		System.out.println("Parent Initializing Block");
	}
	static {
		System.out.println("Parent Static Block");
	}
	
	
	public Parent() {
		
		this(20);
		this.print(); //Constructors must not invoke overridable methods
		System.out.println("Parent :: D C");
	}
	
	private static int parentStatic() {
		System.out.println("Parent :: parentStatic initialization method ");
		return 0;
	}

	public Parent(int i) {
		this.i = i;
		System.out.println("Parent :: A C" +i);
	}
	
	public void call() {
		System.out.println("Parent :: call()");
		this.print();
	}
	
	public void print() {
		System.out.println("Parent :: print()");
		
	}
	
	public abstract Book method() throws FileNotFoundException;
	
	public abstract Java operation();
	
	public static void staticMethod() {
		System.out.println("Parent :: Inside staticMethod()");
		
	}


}
