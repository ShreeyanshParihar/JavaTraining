package com.seclore.main;

import java.util.Scanner;

public class HelloWorldMain {

	public static void main(String[] args) {
		String message;
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("Hello World");

		System.out.print("Enter your message: ");
		message = scanner.nextLine();
		System.out.println("Your message is '" + message + "");

		scanner.close();
	}

}
