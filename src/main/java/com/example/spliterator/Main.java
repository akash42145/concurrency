package com.example.spliterator;

import java.util.Arrays;
import java.util.stream.StreamSupport;

public class Main {
	public static void main(String[] args) {
		Item[][] items;

		items = new Item[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				items[i][j] = new Item();
				items[i][j].setRow(i);
				items[i][j].setColumn(j);
				items[i][j].setName("Item " + i + " " + j);
			}
		}

		MySpliterator mySpliterator = new MySpliterator(items, 0, items.length);

		// System.out.println(mySpliterator.characteristics());

		StreamSupport.stream(mySpliterator, true).forEach(item -> {
			System.out.printf("%s: %s\n", Thread.currentThread().getName(), item.getName());
		});
		
		System.out.println("=======================================");

		Arrays.stream(items).parallel().forEach(item -> Arrays.stream(item).forEach(p -> {
			System.out.printf("%s: %s\n", Thread.currentThread().getName(), p.getName());
		}));
	}

}
