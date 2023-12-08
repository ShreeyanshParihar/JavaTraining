package com.seclore.service;

public class EmailMessageService implements MessageServiceInterface {
	public void sendMessage(String to, String message) {
		System.out.println("Sending Email \"" + message + "\" to \"" + to + "\".");
	}
}
