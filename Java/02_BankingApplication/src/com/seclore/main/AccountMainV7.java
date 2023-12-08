package com.seclore.main;

import java.util.Scanner;

import com.seclore.pojo.Account;
import com.seclore.pojo.Current;
import com.seclore.pojo.Savings;

public class AccountMainV7 {
	public static void main(String[] args) {
		String name;
		double balance;
		boolean isSalary;
		int accountChoice;
		int choice;
		double amount;
		String continueChoice;

		Account account;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Bank Application");

		System.out.println("Select account type:");
		System.out.println("1. Savings");
		System.out.println("2. Current");

		System.out.print("Enter Choice: ");
		accountChoice = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter Name: ");
		name = scanner.nextLine();

		switch (accountChoice) {
		case 1:
			System.out.print("Is this a salary account? [y/n] ");
			isSalary = scanner.next().equalsIgnoreCase("y");
			scanner.nextLine();

			System.out.print("Enter Starting Balance: ");
			balance = scanner.nextDouble();
			scanner.nextLine();

			account = new Savings(name, balance, isSalary);
			break;
		case 2:
			account = new Current(name, 0, 50000);
			break;
		default:
			System.out.println("Invalid choice.");
			scanner.close();
			return;
		}

		do {
			System.out.print("Account Details: ");
			System.out.println(account);

			System.out.println("Menu:");
			System.out.println("1. Get Account Balance");
			System.out.println("2. Withdraw");
			System.out.println("3. Deposit");

			System.out.print("Enter Choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Account Balance: " + account.getBalance());
				break;
			case 2:
				System.out.print("Enter Amount: ");
				amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.println(account.withdraw(amount) ? "Transaction Successful" : "Transaction Failed");
				break;
			case 3:
				System.out.print("Enter Amount: ");
				amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.println(account.deposit(amount) ? "Transaction Successful" : "Transaction Failed");
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
