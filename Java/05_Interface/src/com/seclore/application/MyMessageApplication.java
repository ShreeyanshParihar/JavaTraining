package com.seclore.application;

import com.seclore.service.MessageServiceInterface;

public class MyMessageApplication {
	private MessageServiceInterface messageService;

	public MyMessageApplication(MessageServiceInterface messageService) {
		this.messageService = messageService;
	}

	public void processMessage(String to, String message) {
		messageService.sendMessage(to, message);
	}
}
