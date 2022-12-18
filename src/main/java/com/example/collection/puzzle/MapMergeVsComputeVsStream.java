package com.example.collection.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MapMergeVsComputeVsStream {
	
	private final int value = 5;
	
	private void getVal() {
		int value = 10;
		System.out.println(Thread.currentThread().getName()+":*:- "+this.value);
		
		Runnable r = () -> {
			 //final int value = 15;
			System.out.println(Thread.currentThread().getName()+":#:- "+this.value);
		};
		
		Runnable r1 = new Runnable() {
			
			 private final int value = 15;
			
			@Override
			public void run() {
				int val = 20;
				System.out.println(Thread.currentThread().getName()+":&:- "+this.value);
			}
		};
		
		r.run();
		r1.run();
		new Thread(r,"T1").start();
		
		new Thread(r1,"T2").start();
		
	}

	public static void main(String[] args) throws InterruptedException {
		
		MapMergeVsComputeVsStream mObject = new MapMergeVsComputeVsStream();
		mObject.getVal();
		Thread.sleep(1000);
		//----------------------
		Map<String, String> map = new HashMap<>();

		map.put("Ayana", "Noida");
		map.put("Sharvil", "Motihari");
		map.put("Shikha", "Motihari");
		map.put("Akash", "Narakatiaganj");
		map.put("Purvi", "Noida");
		map.put("Niya", "Noida");

		System.out.println(map);

		// Create a map of City -> List<People> (Map<String, List<String>> )

		// 1. Using old way

		Map<String, List<String>> result = new HashMap<>();

		for (Map.Entry<String, String> entry : map.entrySet()) {

			List<String> list = result.get(entry.getValue());
			if (list == null) {
				list = new ArrayList<>();
				result.put(entry.getValue(), list);
			}
			list.add(entry.getKey());

		}

		System.out.println("Result Map -> " + result);

		// 2. using computeIfAbsent
		Map<String, List<String>> result2 = new HashMap<>();

		for (Map.Entry<String, String> entry : map.entrySet()) {

			result2.computeIfAbsent(entry.getValue(), m -> new ArrayList<>()).add(entry.getKey());

		}

		System.out.println("Result2 Map -> " + result2);

		// 3. java 8 stream

		Map<String, List<String>> result3 = map.entrySet().stream().collect(
				Collectors.groupingBy(Entry::getValue, Collectors.mapping(e -> e.getKey(), Collectors.toList())));

		System.out.println("Result3 Map -> " + result3);

		System.out.println(map.entrySet().stream().collect(Collectors.groupingBy(e -> e.getValue(),
				Collectors.mapping(e -> e.getKey(), Collectors.joining((": "))))));
		
		System.out.println(map.entrySet().stream().collect(Collectors.groupingBy(e -> e.getValue(),
				Collectors.mapping(e -> e.getKey(), Collectors.counting()))));

	}

}
