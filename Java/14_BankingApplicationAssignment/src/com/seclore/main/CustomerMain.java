package com.seclore.main;

import com.seclore.pojo.Account;
import com.seclore.util.IOUtil;

public class CustomerMain {
	public static void main(String[] args) {
		int accountNumber;
		int choice;
		double amount;
		boolean passbookExportChoice;
		boolean continueChoice;

		Account account = null;
		IOUtil io = new IOUtil();

		System.out.println("Bank Application - Customer Portal");

		System.out.println();

		do {
			accountNumber = io.getInt("Enter your account number: ");

			if (accountNumber == 0) {
				System.out.println("Bye.");
				return;
			}

			account = Account.loadFromDisk(accountNumber);
			if (account == null)
				System.out.println("Invalid account number. Try again.");
		} while (account == null);

		do {
			System.out.println("Account Number: " + account.getAccountNumber());

			System.out.println("Menu:");
			System.out.println("1. Show Balance");
			System.out.println("2. Withdraw");
			System.out.println("3. Deposit");
			System.out.println("4. View Passbook");

			choice = io.getInt("Enter Choice: ");

			switch (choice) {
			case 1:
				System.out.println("Account Balance: " + account.getBalance());
				break;
			case 2:
				amount = io.getDouble("Enter Amount: ");
				System.out.println(account.withdraw(amount) ? "Transaction Successful" : "Transaction Failed");
				System.out.print("Saving account...");
				account.saveToDisk();
				System.out.println("Done.");
				break;
			case 3:
				amount = io.getDouble("Enter Amount: ");
				System.out.println(account.deposit(amount) ? "Transaction Successful" : "Transaction Failed");
				System.out.print("Saving account...");
				account.saveToDisk();
				System.out.println("Done.");
				break;
			case 4:
				System.out.println("Transactions for Account " + account.getAccountNumber());
				for (Double transaction : account.getTransactions()) {
					System.out.println((transaction < 0 ? "Withdraw: " : "Deposit: ") + transaction);
				}
				System.out.println("Balance: " + account.getBalance());
				passbookExportChoice = io.getBoolean("Do you want to export the passbook? [Y/n] ");
				if (passbookExportChoice) {
					System.out.println(account.exportPassbook() ? "Passbook exported." : "Some error occurred.");
				}
				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}

			continueChoice = io.getBoolean("Do you want to continue? [Y/n] ");
		} while (continueChoice);
	}
}
