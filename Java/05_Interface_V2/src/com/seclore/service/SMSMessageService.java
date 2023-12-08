package com.seclore.service;

public class SMSMessageService implements MessageServiceInterface {
	public void sendMessage(String to, String message) {
		System.out.println("Sending SMS \"" + message + "\" to \"" + to + "\".");
	}
}
