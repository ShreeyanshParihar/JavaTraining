package com.seclore.pojo;

public class CashRegister {
	private int cashOnHand;

	public CashRegister() {
		this.cashOnHand = 500;
	}

	public CashRegister(int amount) {
		this.cashOnHand = amount;
	}

	public int getCurrentBalance() {
		return this.cashOnHand;
	}

	public void acceptAmount(int amount) {
		this.cashOnHand += amount;
	}
}
