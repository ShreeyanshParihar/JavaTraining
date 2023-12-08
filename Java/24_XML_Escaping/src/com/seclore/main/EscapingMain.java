package com.seclore.main;

import org.apache.commons.lang.StringEscapeUtils;

public class EscapingMain {
	public static void main(String[] args) {
		printEscapedAndUnescaped("Java & XML");
		printEscapedAndUnescaped("Java > XML");
		printEscapedAndUnescaped("Java < XML");
		printEscapedAndUnescaped("Java \' XML");
		printEscapedAndUnescaped("Java \" XML");
	}

	public static void printEscapedAndUnescaped(String unescaped) {
		System.out.println("Uneascaped: " + unescaped);
		System.out.println("Escaped: " + StringEscapeUtils.escapeXml(unescaped));
		System.out.println();
	}
}
