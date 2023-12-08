package com.seclore.main;

import com.seclore.pojo.Account;
import com.seclore.pojo.Current;
import com.seclore.pojo.Savings;
import com.seclore.util.IOUtil;

public class AccountOpeningMain {
	public static void main(String[] args) {
		String name;
		double balance;
		boolean isSalary;
		int accountChoice;

		Account account;
		IOUtil io = new IOUtil();

		System.out.println("Bank Application - Account Opening Portal");

		System.out.println();

		System.out.println("Select account type:");
		System.out.println("1. Savings");
		System.out.println("2. Current");

		accountChoice = io.getInt("Enter Choice: ");

		switch (accountChoice) {
		case 1:
			name = io.getString("Enter Name: ");
			isSalary = io.getBoolean("Is this a salary account? [Y/n] ");
			balance = io.getDouble("Enter Starting Balance: ");

			account = new Savings(name, balance, isSalary);
			break;
		case 2:
			name = io.getString("Enter Name: ");
			account = new Current(name, 0, 50000);
			break;
		default:
			System.out.println("Invalid choice.");
			return;
		}
		System.out.println("Account created.");
		System.out.println("Your account number: " + account.getAccountNumber());

		System.out.print("Saving account...");
		account.saveToDisk();
		System.out.println("Done.");
	}
}
