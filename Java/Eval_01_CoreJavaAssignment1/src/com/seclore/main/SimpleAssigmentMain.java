package com.seclore.main;

import com.seclore.assignment.SimpleAssigment;

public class SimpleAssigmentMain {

	public static void main(String[] args) {
		SimpleAssigment assigment = new SimpleAssigment();

		char[] firstName = "divya".toCharArray();
		char[] lastName = "jain".toCharArray();

		char[] fullName = assigment.concatenate(firstName, lastName);
		System.out.print("concatenate('divya', 'jain') -> ");
		printCharArray(fullName);

		System.out.print("concatenate('abcd', 'abdc') -> ");
		printCharArray(assigment.concatenate("abcd".toCharArray(), "abdc".toCharArray()));

		System.out.print("concatenate(null, 'abdc') -> ");
		printCharArray(assigment.concatenate(null, "abdc".toCharArray()));

		System.out.println();

		char[] userName = assigment.concatenate(firstName, lastName, 1);
		System.out.print("concatenate('divya', 'jain', 1) -> ");
		printCharArray(userName);

		System.out.println();

		System.out.println("find('divya', 'v') -> " + assigment.find(firstName, 'v'));
		System.out.println("find('divya', 'x') -> " + assigment.find(firstName, 'x'));

		System.out.println();

		System.out.println("compare('divya', 'divyajain') -> " + assigment.compare(firstName, fullName));
		System.out.println("compare('divya', 'divya') -> " + assigment.compare(firstName, firstName));
		System.out.println("compare('xyz', 'ab') -> " + assigment.compare("xyz".toCharArray(), "ab".toCharArray()));

		System.out.println();

		System.out.println("compare('divya', 'divyajain', 4) -> " + assigment.compare(firstName, fullName, 4));
		System.out
				.println("compare('xyz', 'ab', 5) -> " + assigment.compare("xyz".toCharArray(), "ab".toCharArray(), 5));

		System.out.println();

		assigment.copy(firstName, "abcd".toCharArray());
		System.out.print("copy('divya', 'abcd') -> ");
		printCharArray(firstName);

		assigment.copy(fullName, "abcdefghijklm".toCharArray());
		System.out.print("copy('divyajain', 'abcdefghijklm') -> ");
		printCharArray(fullName);

		System.out.println();

		System.out.print("swapChars('security') -> ");
		printCharArray(assigment.swapChars("security".toCharArray()));

		System.out.print("swapChars('onetwofour') -> ");
		printCharArray(assigment.swapChars("onetwofour".toCharArray()));

		System.out.println();
		
		System.out.print("mixChars('abcde', 'pqrst') -> ");
		printCharArray(assigment.mixChars("abcde".toCharArray(), "pqrst".toCharArray()));

		System.out.print("mixChars('seclore', 'techies') -> ");
		printCharArray(assigment.mixChars("seclore".toCharArray(), "techies".toCharArray()));
	}

	public static void printCharArray(char[] text) {
		if (text == null) {
			System.out.println("null");
			return;
		}

		System.out.print("'");
		for (char c : text)
			System.out.print(c);
		System.out.println("'");
	}

}
