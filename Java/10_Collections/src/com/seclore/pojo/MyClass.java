package com.seclore.pojo;

public class MyClass<T extends Comparable<T>> {
	private T myVariable;

	public MyClass(T myVariable) {
		super();
		this.myVariable = myVariable;
	}

	public void display() {
		System.out.println(myVariable);
	}
}