package com.seclore.main;

import com.seclore.pojo.Account;

public class AccountMain {
	public static void main(String[] args) {
		Account account = new Account();

		account.setName("Divy");
		account.setAccountNumber(420);
		account.setBalance(69000);
		
		System.out.println(account);
	}
}
