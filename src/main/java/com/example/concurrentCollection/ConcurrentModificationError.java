package com.example.concurrentCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationError {

	public static void main(String[] args) {
		
		Integer[] arr = {1,5,9,8,3};
		List<Integer> lst =Arrays.asList(arr);
		
		lst.set(0, 101);
		arr[1]= 99;
		
		System.out.println(Arrays.deepToString(arr) + " "+ lst);
		
		List<Integer> listInt = new ArrayList<Integer>();
		listInt.add(1);
		listInt.add(11);
		listInt.add(111);
		
		List<Integer> syncList = Collections.synchronizedList(listInt);
		synchronized (syncList) {
			for(Integer i : syncList) {
				//syncList.add(12);
				
			}
		}
		
		Iterator<Integer> itr = syncList.iterator();
		while(itr.hasNext()) {
			itr.next();
			itr.remove();
		}
		System.out.println(syncList);
		
		
		

	}

}
