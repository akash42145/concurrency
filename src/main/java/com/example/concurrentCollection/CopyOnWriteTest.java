package com.example.concurrentCollection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteTest {

	public static void main(String[] args) {
		List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(5,12,8,6));
		
		
		
		for(int i : list) {
			System.out.println(i +" :: "+ list);
			list.add(101);
		}
		
		System.out.println("Size "+ list.size());
		
		Iterator<Integer> iterator = list.iterator();
		
		iterator.forEachRemaining( i -> {
			System.out.println(i);
			list.add(91);
			//list.remove(new Integer(5));
			iterator.remove();
		});
		
		System.out.println(" :: "+ list);
	}

}
