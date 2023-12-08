package com.seclore.main;

import com.seclore.pojo.Current;

public class AccountMainV5 {
	public static void main(String[] args) {
		Current current = new Current(101, "Divy", 10000, 50000);

		System.out.println(current);
		System.out.println("---------------");
		System.out.println("Withdraw: 5000");
		current.withdraw(5000);
		System.out.println(current);
		System.out.println("---------------");
		System.out.println("Withdraw: 10000");
		current.withdraw(10000);
		System.out.println(current);
		System.out.println("---------------");
		System.out.println("Deposit: 2000");
		current.deposit(2000);
		System.out.println(current);
		System.out.println("---------------");
		System.out.println("Deposit: 25000");
		current.deposit(25000);
		System.out.println(current);
		System.out.println("---------------");
	}
}
