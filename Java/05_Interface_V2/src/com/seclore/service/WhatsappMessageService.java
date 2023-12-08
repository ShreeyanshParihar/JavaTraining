package com.seclore.service;

public class WhatsappMessageService implements MessageServiceInterface {
	public void sendMessage(String to, String message) {
		System.out.println("Sending Whatsapp \"" + message + "\" to \"" + to + "\".");
	}
}
