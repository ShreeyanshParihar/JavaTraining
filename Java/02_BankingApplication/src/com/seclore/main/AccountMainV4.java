package com.seclore.main;

import java.util.Scanner;

import com.seclore.pojo.Savings;

public class AccountMainV4 {
	public static void main(String[] args) {
		int accountNumber;
		String name;
		double balance;
		boolean isSalary;
		int choice;
		double amount;
		String continueChoice;

		Savings savings;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Bank Application");
		
		System.out.print("Enter Account Number: ");
		accountNumber = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter Name: ");
		name = scanner.nextLine();

		System.out.print("Enter Balance: ");
		balance = scanner.nextDouble();
		scanner.nextLine();
		
		System.out.print("Is this a salary account? [y/n] ");
		isSalary = scanner.next().equalsIgnoreCase("y");
		scanner.nextLine();
		
		savings = new Savings(accountNumber, name, balance, isSalary);

		do {
			System.out.print("Account Details: ");
			System.out.println(savings);
			
			System.out.println("Menu:");
			System.out.println("1. Get Account Balance");
			System.out.println("2. Withdraw");
			System.out.println("3. Deposit");

			System.out.print("Enter Choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Account Balance: " + savings.getBalance());
				break;
			case 2:
				System.out.print("Enter Amount: ");
				amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.println(savings.withdraw(amount) ? "Transaction Successful" : "Transaction Failed");
				break;
			case 3:
				System.out.print("Enter Amount: ");
				amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.println(savings.deposit(amount) ? "Transaction Successful" : "Transaction Failed");
				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}

			System.out.print("Do you want to continue? [y/n] ");
			continueChoice = scanner.next();
			scanner.nextLine();
		} while (continueChoice.equalsIgnoreCase("y"));
		scanner.close();
	}
}
