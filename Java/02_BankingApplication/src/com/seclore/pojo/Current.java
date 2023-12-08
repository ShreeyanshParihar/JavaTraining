package com.seclore.pojo;

public class Current extends Account {
	private double overdraftBalance;
	private double overdraftLimit;

	public Current(int accountNumber, String name) {
		super(accountNumber, name);
	}
	
	public Current(int accountNumber, String name, double balance) {
		super(accountNumber, name, balance);
	}

	public Current(String name, double balance, double overdraftBalance) {
		super(name, balance);
		this.overdraftBalance = overdraftBalance;
		this.overdraftLimit = overdraftBalance;
	}
	
	public Current(int accountNumber, String name, double balance, double overdraftBalance) {
		super(accountNumber, name, balance);
		this.overdraftBalance = overdraftBalance;
		this.overdraftLimit = overdraftBalance;
	}

	public double getOverdraftBalance() {
		return overdraftBalance;
	}

	public void setOverdraftBalance(double overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}

	@Override
	public boolean withdraw(double amount) {
		double balance = this.getBalance();
		if (amount > 0 && amount <= balance + overdraftBalance) {
			double newBalance = balance - amount;
			this.setBalance(Math.max(newBalance, 0));
			this.overdraftBalance += Math.min(0, newBalance);

			return true;
		}
		return false;
	}

	@Override
	public boolean deposit(double amount) {
		double balance = this.getBalance();
		if (amount > 0) {
			double newOverdraftBalance = overdraftBalance + amount;
			this.setBalance(balance + Math.max(0, newOverdraftBalance - overdraftLimit));
			this.overdraftBalance = Math.min(newOverdraftBalance, overdraftLimit);

			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Current [overdraftBalance=" + overdraftBalance + ", accountNumber=" + getAccountNumber() + ", name="
				+ getName() + ", balance=" + getBalance() + "]";
	}

}
