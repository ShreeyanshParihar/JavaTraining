package com.seclore.assignment;

public class SimpleAssigment {
	public char[] concatenate(char[] pstrDest, char[] pstrText) {
		if (pstrDest == null) {
			if (pstrText == null) {
				return null;
			}
			return clone(pstrText, 0, pstrText.length);
		}
		if (pstrText == null) {
			return clone(pstrDest, 0, pstrDest.length);
		}

		char[] result = new char[pstrDest.length + pstrText.length];

		copy(result, 0, pstrDest.length, pstrDest, 0, pstrDest.length);
		copy(result, pstrDest.length, result.length, pstrText, 0, pstrText.length);

		return result;
	}

	public char[] concatenate(char[] pstrDest, char[] pstrText, int piCount) {
		if (pstrDest == null) {
			if (pstrText == null) {
				return null;
			}
			return clone(pstrText, 0, pstrText.length);
		}
		if (pstrText == null || piCount < 1) {
			return clone(pstrDest, 0, pstrDest.length);
		}

		int charsToConcatenate = Math.min(pstrText.length, piCount);
		// Clipping pstrText to piCount and then using base concatenate function
		return concatenate(pstrDest, clone(pstrText, 0, charsToConcatenate));
	}

	public void copy(char[] pstrDest, char[] pstrSrc) {
		if (pstrDest == null || pstrSrc == null)
			return;

		copy(pstrDest, 0, pstrDest.length, pstrSrc, 0, pstrSrc.length);
	}

	// Private copy helper function
	private void copy(char[] pstrDest, int destFrom, int destTo, char[] pstrSrc, int srcFrom, int srcTo) {
		int length = Math.min(destTo - destFrom, srcTo - srcFrom);

		for (int i = 0; i < length; i++)
			pstrDest[destFrom + i] = pstrSrc[srcFrom + i];
	}

	public int find(char[] pstrText, char pchSearch) {
		if (pstrText == null)
			return -1;

		for (int i = 0; i < pstrText.length; i++)
			if (pstrText[i] == pchSearch)
				return i;
		return -1;
	}

	public int compare(char[] pstrText1, char[] pstrText2) {
		// Considering null as lexically smallest
		if (pstrText1 == null) {
			if (pstrText2 == null)
				return 0;
			return -1;
		} else if (pstrText2 == null)
			return 1;

		int length = Math.min(pstrText1.length, pstrText2.length);

		for (int i = 0; i < length; i++)
			if (pstrText1[i] != pstrText2[i])
				return pstrText1[i] - pstrText2[i];

		return pstrText1.length - pstrText2.length;
	}

	public int compare(char[] pstrText1, char[] pstrText2, int piCount) {
		// Considering null as lexically smallest
		if (pstrText1 == null) {
			if (pstrText2 == null)
				return 0;
			return -1;
		} else if (pstrText2 == null)
			return 1;

		int length = Math.min(piCount, Math.min(pstrText1.length, pstrText2.length));

		for (int i = 0; i < length; i++)
			if (pstrText1[i] != pstrText2[i])
				return pstrText1[i] - pstrText2[i];

		if (length == piCount)
			return 0;

		return pstrText1.length - pstrText2.length;
	}

	// Private clone helper function
	private char[] clone(char[] originalText, int fromIndex, int toIndex) {
		char[] clonedText = new char[toIndex - fromIndex];

		copy(clonedText, 0, clonedText.length, originalText, fromIndex, toIndex);

		return clonedText;
	}

	// Given assumption: length is a multiple of 2
	public char[] swapChars(char[] pstrText) {
		if (pstrText == null)
			return null;

		if (pstrText.length == 0)
			return new char[] {};

		char[] swappedChars = new char[] { pstrText[1], pstrText[0] };
		char[] remainingPstrText = clone(pstrText, 2, pstrText.length);

		return concatenate(swappedChars, swapChars(remainingPstrText));
	}

	// Given assumption: pstrText1.length == pstrText2.length
	public char[] mixChars(char[] pstrText1, char[] pstrText2) {
		if (pstrText1 == null || pstrText2 == null)
			return null;

		if (pstrText1.length == 0)
			return new char[] {};

		char[] mixedChars = new char[] { pstrText1[0], pstrText2[0] };
		char[] remainingPstrText1 = clone(pstrText1, 1, pstrText1.length);
		char[] remainingPstrText2 = clone(pstrText2, 1, pstrText2.length);

		return concatenate(mixedChars, mixChars(remainingPstrText1, remainingPstrText2));
	}

}
