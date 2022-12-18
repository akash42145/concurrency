package com.example.concurrentCollection;

import java.util.concurrent.ConcurrentHashMap;

public class CHMMethods {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<String, Integer>();

		chm.put("A", 1);
		chm.put("B", 1);
		chm.put("C", 1);

		chm.computeIfAbsent("D", k -> {
			System.out.println(k);
			return 10;
		});
		System.out.println(chm.computeIfAbsent("G", k -> 12));
		System.out.println(chm.computeIfPresent("A", (k, v) -> chm.get(k) + 1));
		System.out.println(chm.putIfAbsent("K", 111));

		System.out.println(chm);
	}

}
