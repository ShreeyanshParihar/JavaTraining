package com.seclore.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorldLoggerMain {
	private static final Logger LOGGER = LogManager.getLogger(HelloWorldLoggerMain.class);

	public static void main(String[] args) {
		for (int i = 0; i < 100000; i++) {
			LOGGER.trace("This is trace");
			LOGGER.debug("This is debug");
			LOGGER.info("This is info");
			LOGGER.warn("This is warn");
			LOGGER.error("This is error");
			LOGGER.fatal("This is fatal");
		}
	}
}
