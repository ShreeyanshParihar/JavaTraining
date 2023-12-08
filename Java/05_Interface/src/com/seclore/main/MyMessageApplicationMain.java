package com.seclore.main;

import java.util.Scanner;

import com.seclore.application.MyMessageApplication;
import com.seclore.factory.ApplicationFactory;

public class MyMessageApplicationMain {
	public static void main(String[] args) {
		int messageChoice;
		String to;
		String message;
		Scanner scanner = new Scanner(System.in);
		MyMessageApplication app;

		System.out.println("Message Application");

		System.out.println("Select message type:");
		System.out.println("1. SMS");
		System.out.println("2. Email");
		System.out.println("3. Whatsapp");

		System.out.print("Enter Choice: ");
		messageChoice = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter Recipent: ");
		to = scanner.nextLine();
		
		System.out.print("Enter Message: ");
		message = scanner.nextLine();
		
		app = ApplicationFactory.getApplication(messageChoice);
		app.processMessage(to, message);

		scanner.close();
	}

}
