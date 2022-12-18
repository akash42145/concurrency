package com.example.collection.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle1 {

	public static void main(String[] args) {
		
		List<Integer> lst = List.of(1, 0, 5, -1);
		
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		lst.forEach(number -> {
			map.computeIfAbsent(number, ArrayList::new).add(number);
			//map.computeIfAbsent(number, n -> new ArrayList<Integer>()).add(number);
		});
		
		System.out.println(map);

	}

}
