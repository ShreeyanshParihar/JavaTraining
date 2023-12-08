package com.seclore.main;

import com.seclore.pojo.Account;

public class AccountMainV2 {
	public static void main(String[] args) {
		Account account1, account2;

		account1 = new Account();
		account2 = account1;

		System.out.println(account1);
		System.out.println(account2);

		account2.setBalance(69420);

		System.out.println("----------");
		System.out.println(account1);
		System.out.println(account2);
	}
}
