package com.seclore.assignment;

import java.util.Map;
import java.util.HashMap;

public class Assignment {
	private static Map<Character, Integer> countCharacterOccurences(String string) {
		Map<Character, Integer> occurences = new HashMap<Character, Integer>();

		for (Character character : string.toCharArray()) {
			occurences.put(character, occurences.getOrDefault(character, 0) + 1);
		}

		return occurences;
	}

	public static String customSubstring(String[] strArr) {
		StringBuffer N = new StringBuffer(strArr[0]);
		Map<Character, Integer> charsInK = countCharacterOccurences(strArr[1]);

		for (int substringLength = charsInK.size(); substringLength < N.length(); substringLength++) {
			substringLoop: for (int startFrom = 0; startFrom + substringLength <= N.length(); startFrom++) {
				String substring = N.substring(startFrom, startFrom + substringLength);
				Map<Character, Integer> substringChars = countCharacterOccurences(substring);

				for (Map.Entry<Character, Integer> characterEntry : charsInK.entrySet()) {
					boolean isCharPresent = substringChars.getOrDefault(characterEntry.getKey(), 0) >= characterEntry
							.getValue();

					if (!isCharPresent) {
						{
							continue substringLoop;
						}
					}

					return substring;
				}
			}
		}

		return N.toString();
	}

	public static int myBracketMatcher(String str) {
		int counter = 0;

		for (Character character : str.toCharArray()) {
			if (character == '(') {
				counter++;
			} else if (character == ')') {
				counter--;
			}

			if (counter < 0) {
				return 1;
			}
		}

		return (counter == 0) ? 0 : 1;
	}

	public static String longestWord(String sen) {
		String longestWord = "";

		for (String word : sen.split("[^\\p{IsAlphabetic}]")) {
			if (word.length() > longestWord.length()) {
				longestWord = word;
			}
		}

		return longestWord;
	}
}
