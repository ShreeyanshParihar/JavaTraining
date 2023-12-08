package com.seclore.pojo;

public class Fruit implements Comparable<Fruit> {
	private String name;
	private String description;
	private int quantity;
	
	public Fruit() {
	}
	
	public Fruit(String name, String description, int quantity) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Fruit [name=" + name + ", description=" + description + ", quantity=" + quantity + "]";
	}

	@Override
	public int compareTo(Fruit otherFruit) {
		return name.compareTo(otherFruit.getName());
	}
	
}
