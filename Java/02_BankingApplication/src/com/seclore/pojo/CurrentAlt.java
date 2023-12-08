package com.seclore.pojo;

public class CurrentAlt extends Account {
	private double overdraftLimit;

	@Override
	public double getBalance() {
		return Math.max(super.getBalance(), 0);
	}

	public double getOverdraftBalance() {
		return Math.min(overdraftLimit, super.getBalance() + overdraftLimit);
	}

	public CurrentAlt(int accountNumber, String name) {
		super(accountNumber, name);
	}

	public CurrentAlt(int accountNumber, String name, double balance) {
		super(accountNumber, name, balance);
	}

	public CurrentAlt(int accountNumber, String name, double balance, double overdraftBalance) {
		super(accountNumber, name, balance);
		this.overdraftLimit = overdraftBalance;
	}

	@Override
	public boolean withdraw(double amount) {
		double balance = super.getBalance();
		if (amount > 0 && amount <= balance + overdraftLimit) {
			this.setBalance(balance - amount);
			return true;
		}
		return false;
	}

	@Override
	public boolean deposit(double amount) {
		double balance = super.getBalance();
		if (amount > 0) {
			this.setBalance(balance + amount);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Current [overdraftBalance=" + getOverdraftBalance() + ", accountNumber=" + getAccountNumber() + ", name="
				+ getName() + ", balance=" + getBalance() + "]";
	}

}
