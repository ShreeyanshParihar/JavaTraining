package com.seclore.factory;

import com.seclore.application.MyMessageApplication;
import com.seclore.service.EmailMessageService;
import com.seclore.service.MessageServiceInterface;
import com.seclore.service.SMSMessageService;
import com.seclore.service.WhatsappMessageService;

public class ApplicationFactory {
	public static MyMessageApplication getApplication(int messageType) {
		return new MyMessageApplication(messageServiceFactory(messageType));
	}

	public static MessageServiceInterface messageServiceFactory(int messageType) {
		switch (messageType) {
		case 1:
			return new SMSMessageService();
		case 2:
			return new EmailMessageService();
		case 3:
			return new WhatsappMessageService();
		default:
			return null;
		}
	}
}
