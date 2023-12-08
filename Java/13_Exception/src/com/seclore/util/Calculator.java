package com.seclore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Calculator {
	int num1 = 0, num2 = 0, result;

	public void acceptNumbers() {
		Scanner scanner = new java.util.Scanner(System.in);

		System.out.print("Enter number 1: ");
		num1 = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter number 2: ");
		num2 = scanner.nextInt();
		scanner.nextLine();

		scanner.close();
	}

	public void calculateResult() {
		try {
			result = num1 / num2;
		} catch (ArithmeticException e) {
			System.out.println("Diving by zero :(");
			result = 0;
		}
	}

	public void displayResult() {
		System.out.println("Result: " + result);
	}

	public void test() throws ClassNotFoundException, IOException {
		InputStream inputStream = new FileInputStream(new File("test.txt"));
		inputStream.close();
		Class.forName("com.seclore.util.Random");
	}
}
