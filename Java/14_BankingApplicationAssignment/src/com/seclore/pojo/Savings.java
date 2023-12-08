package com.seclore.pojo;

public class Savings extends Account {
	private static final long serialVersionUID = 2630053456515857001L;
	private boolean isSalary;
	
	public Savings(String name, double balance, boolean isSalary) {
		super(name, balance);
		this.isSalary = isSalary;
	}

	public boolean isSalary() {
		return isSalary;
	}

	public void setSalary(boolean isSalary) {
		this.isSalary = isSalary;
	}

	@Override
	public boolean withdraw(double amount) {
		double balance = super.getBalance();
		if (amount > 0 && amount <= balance && (this.isSalary || this.getBalance() - amount >= 1500)) {
			super.setBalance(balance - amount);
			return true;
		}
		return false;
	}

	@Override
	public boolean deposit(double amount) {
		double balance = super.getBalance();
		if (amount > 0) {
			super.setBalance(balance + amount);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Savings [isSalary=" + isSalary + ", accountNumber=" + getAccountNumber() + ", name="
				+ getName() + ", balance=" + getBalance() + "]";
	}
	
	

	
}
