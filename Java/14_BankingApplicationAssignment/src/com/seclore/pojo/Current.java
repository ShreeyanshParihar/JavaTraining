package com.seclore.pojo;

public class Current extends Account {
	private static final long serialVersionUID = 116644617936280043L;
	private double overdraftLimit;

	public Current(String name, double balance, double overdraftLimit) {
		super(name, balance);
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	public double getBalance() {
		return Math.max(super.getBalance(), 0);
	}

	public double getOverdraftBalance() {
		return Math.min(overdraftLimit, super.getBalance() + overdraftLimit);
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
		return "Current [overdraftBalance=" + getOverdraftBalance() + ", accountNumber=" + getAccountNumber()
				+ ", name=" + getName() + ", balance=" + getBalance() + "]";
	}

}
