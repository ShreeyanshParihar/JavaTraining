package com.seclore.main;

public class StringVsStringBufferMainV1 {

	public static void main(String[] args) {
		System.out.println("----------------");
		System.out.println(" Testing String");
		System.out.println("----------------");

		String msg = "I love Java";
		System.out.println(msg);
		System.out.println("Hashcode: " + msg.hashCode());

		System.out.println();

		msg = msg.concat(", it is the best.");
		System.out.println(msg);
		System.out.println("Hashcode: " + msg.hashCode());

		System.out.println();

		System.out.println("----------------------");
		System.out.println(" Testing StringBuffer");
		System.out.println("----------------------");

		StringBuffer bufferMsg = new StringBuffer("I love Java");
		System.out.println(bufferMsg);
		System.out.println("Hashcode: " + bufferMsg.hashCode());

		System.out.println();

		bufferMsg = bufferMsg.append(", it is the best.");
		System.out.println(bufferMsg);
		System.out.println("Hashcode: " + bufferMsg.hashCode());
	}

}
