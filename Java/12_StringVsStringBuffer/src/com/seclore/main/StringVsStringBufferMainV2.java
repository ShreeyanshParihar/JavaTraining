package com.seclore.main;

public class StringVsStringBufferMainV2 {

	public static String concatUsingString() {
		String msg = "I love";
		for (int i = 0; i < 100_000; i++) {
			msg = msg + " Java";
		}
		return msg;
	}

	public static String concatUsingStringBuffer() {
		StringBuffer msg = new StringBuffer("I love");
		for (int i = 0; i < 100_000; i++) {
			msg = msg.append(" Java");
		}
		return msg.toString();
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		concatUsingStringBuffer();
		System.out.println("Time(concatUsingStringBuffer): " + (System.currentTimeMillis() - startTime) + "ms");

		startTime = System.currentTimeMillis();
		concatUsingString();
		System.out.println("Time(concatUsingString): " + (System.currentTimeMillis() - startTime) + "ms");
	}

}
