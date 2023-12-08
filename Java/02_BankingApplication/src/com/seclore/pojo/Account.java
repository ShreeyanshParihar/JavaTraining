package com.seclore.pojo;

public abstract class Account extends Object {
	private int accountNumber;
	private String name;
	private double balance;

	private static int lastAccountNumber = 100;

	public Account(String name, double balance) {
		this.accountNumber = Account.lastAccountNumber++;
		this.name = name;
		this.balance = balance;
	}

	public Account(int accountNumber, String name) {
		this.accountNumber = accountNumber;
		this.name = name;
	}

	public Account(int accountNumber, String name, double balance) {
		this.accountNumber = accountNumber;
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
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", name=" + name + ", balance=" + balance + "]";
	}

}
