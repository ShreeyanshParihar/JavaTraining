package com.seclore.pojo;

public class DispenserType {
	private int numberOfItems;
	private int cost;

	public DispenserType() {
		this.numberOfItems = 50;
		this.cost = 50;
	}

	public DispenserType(int numberOfItems, int cost) {
		this.numberOfItems = numberOfItems;
		this.cost = cost;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public int getCost() {
		return cost;
	}
	
	public void makeSale() {
		this.numberOfItems -= 1;
	}
}
