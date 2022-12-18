package com.example.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamReuseTest {

	public static void main(String[] args) {
		
		Stream s = getStream();
		
		System.out.println(s.count() + s.count());

	}

	private static Stream getStream() {
		List<String> l = new ArrayList<>();
		
		l.add(null);
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		l.add("5");
		
		for(String s : l) {
			System.out.println(s);
		}
		
		return l.stream();
	}

}
