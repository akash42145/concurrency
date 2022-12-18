package com.example.OverloadingOverriding;

public class Client {

	public static void main(String[] args) {
		Parent ch = new Child();
		ch.call();
		System.out.println("-------------------");
		Child pr = new Child();
		pr.call();
				
		// Expected Output:
				
		/* 
		 Parent Static Block
		 Child Static Block		 
		 Parent Initializing Block
		 Parent :: A C 20
		 Parent :: D C
		 Child Initializing Block
		 Child :: AC 10
		 Child :: DC
		 Child :: call()		 
		 Parent :: call()
		 Child :: print()
		 -------------------
		 Parent Initializing Block
		 Parent :: A C 20
		 Parent :: D C
		 Child Initializing Block
		 Child :: AC 10
		 Child :: DC
		 Child :: call()		 
		 Parent :: call()
		 Child :: print()
		 */

	}

}
