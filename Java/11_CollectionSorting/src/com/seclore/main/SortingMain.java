package com.seclore.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.seclore.pojo.Fruit;

public class SortingMain {

	public static void main(String[] args) {
		String[] fruitsArray = new String[] { "Pineapple", "Apple", "Orange", "Banana" };

		Arrays.sort(fruitsArray);

		for (String fruit : fruitsArray) {
			System.out.println(fruit);
		}

		List<String> fruitsList = new ArrayList<String>();
		fruitsList.add("Pineapple");
		fruitsList.add("Apple");
		fruitsList.add("Orange");
		fruitsList.add("Banana");

		Collections.sort(fruitsList, Collections.reverseOrder());

		System.out.println(fruitsList);

		Fruit[] fruits = new Fruit[4];

		fruits[0] = new Fruit("Pinapple", "desc", 100);
		fruits[1] = new Fruit("Apple", "desc", 70);
		fruits[2] = new Fruit("Orange", "desc", 30);
		fruits[3] = new Fruit("Banana", "desc", 50);

		Arrays.sort(fruits);
		
		for (Fruit fruit : fruits) {
			System.out.println(fruit);
		}
	}

}
