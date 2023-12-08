package com.seclore.pojo;

public class MyClass {
	private static int num1 = 10;
	private int num2 = 15;
	private static int num3 = 30;

	public MyClass() {
		System.out.println("constructor");
	}

	{
		System.out.println("non-static block");
	}
	
	static {
		System.out.println("static block");
	}
	
	public void display() {
		System.out.println("num1: "+num1);
		System.out.println("num2: "+num2);
		num1++;
		num2++;
		System.out.println("num1: "+num1);
		System.out.println("num2: "+num2);
	}
	
	public static void staticDisplay() {
		System.out.println("num1: "+num1);
		System.out.println("num3: "+num3);
		num1++;
		num3++;
		System.out.println("num1: "+num1);
		System.out.println("num3: "+num3);
	}
}
