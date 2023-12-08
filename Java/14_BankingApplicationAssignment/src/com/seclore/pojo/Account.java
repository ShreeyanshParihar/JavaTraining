package com.seclore.pojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Account implements Serializable {
	private static final long serialVersionUID = 3675732619927488443L;

	private int accountNumber;
	private String name;
	private double balance;
	private List<Double> transactions = new ArrayList<Double>();

	private static int nextAccountNumber = Account.getNextAccountNumberPrefs();

	public Account(String name, double balance) {
		this.accountNumber = Account.nextAccountNumber++;
		this.name = name;
		this.balance = balance;
	}

	public abstract boolean withdraw(double amount);

	public abstract boolean deposit(double amount);

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		transactions.add(this.balance - balance);
		this.balance = balance;
	}

	public List<Double> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Double> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", name=" + name + ", balance=" + balance + "]";
	}

	// IO Operations

	public boolean saveToDisk() {
		String filepath = "data/account_" + this.accountNumber + ".txt";
		ObjectOutputStream objectOutputStream = null;

		try {
			// Save account
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(filepath)));
			objectOutputStream.writeObject(this);

			// Save highest account number
			int nextAccountNumber = Account.getNextAccountNumberPrefs();
			if (Account.nextAccountNumber > nextAccountNumber) {
				FileWriter prefsWriter = new FileWriter("prefs/nextAccountNumber.txt");
				prefsWriter.write(String.valueOf(Account.nextAccountNumber));
				prefsWriter.close();
			}

			return true;
		} catch (IOException e) {
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
			}
		}
		return false;
	}

	public boolean exportPassbook() {
		FileWriter prefsWriter = null;
		try {
			prefsWriter = new FileWriter("export/passbook_" + this.accountNumber + ".txt");
			prefsWriter.write("Passbook (Account " + this.accountNumber + ")\n");
			prefsWriter.write("----------------------\n");
			for (Double transaction : transactions) {
				prefsWriter.write((transaction < 0 ? "Withdraw: " : "Deposit: ") + transaction + "\n");
			}
			prefsWriter.write("----------------------\n");
			prefsWriter.write("Balance: " + this.balance);
			return true;
		} catch (IOException e) {
		} finally {
			try {
				prefsWriter.close();
			} catch (IOException e) {
				return false;
			}
		}
		return false;
	}

	public static Account loadFromDisk(int accountNumber) {
		String filepath = "data/account_" + accountNumber + ".txt";
		File accountFile = new File(filepath);
		ObjectInputStream objectInputStream = null;

		if (!(accountFile.exists() && accountFile.isFile())) {
			return null;
		}

		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(accountFile));
			return (Account) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				objectInputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;

	}

	public static int getNextAccountNumberPrefs() {
		File prefsFile = new File("prefs/nextAccountNumber.txt");
		Scanner prefsScanner = null;
		try {
			prefsFile.createNewFile();
			prefsScanner = new Scanner(new FileInputStream(prefsFile));
			return prefsScanner.nextInt();
		} catch (Exception e) {
			return 100;
		} finally {
			prefsScanner.close();
		}

	}

}
