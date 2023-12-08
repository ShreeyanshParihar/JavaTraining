package com.seclore.main;

import com.seclore.assignment.Assignment;

public class AssignmentMain {

	public static void main(String[] args) {
		System.out.println("customSubstring(\"aaabaaddae\", \"aed\") -> "
				+ Assignment.customSubstring(new String[] { "aaabaaddae", "aed" }));
		System.out.println("customSubstring(\"aabdccdbcacd\", \"aad\") -> "
				+ Assignment.customSubstring(new String[] { "aabdccdbcacd", "aad" }));
		System.out.println("customSubstring(\"ahffaksfajeeubsne\", \"jefaa\") -> "
				+ Assignment.customSubstring(new String[] { "ahffaksfajeeubsne", "jefaa" }));
		System.out.println("customSubstring(\"aaffhkksemckelloe\", \"fhea\") -> "
				+ Assignment.customSubstring(new String[] { "aaffhkksemckelloe", "fhea" }));

		System.out.println();

		System.out.println("myBrakcerMatcher(\"(coder)(byte))\") -> " + Assignment.myBracketMatcher("(coder)(byte))"));
		System.out.println("myBrakcerMatcher(\"(c(oder)) b(yte)\") -> " + Assignment.myBracketMatcher("(c(oder)) b(yte)"));

		System.out.println();

		System.out.println("longestWord(\"fun&!! time\") -> " + Assignment.longestWord("fun&!! time"));
		System.out.println("myBrakcerMatcher(\"I love cats\") -> " + Assignment.longestWord("I love cats"));
	}

}
