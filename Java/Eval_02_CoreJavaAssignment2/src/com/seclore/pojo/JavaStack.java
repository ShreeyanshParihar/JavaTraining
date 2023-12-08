package com.seclore.pojo;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.seclore.exceptions.NoMoreElementException;

public class JavaStack<T> {
	private Vector<T> vector;

	public JavaStack() {
		vector = new Vector<T>();
	}

	public T pop() throws NoMoreElementException {
		try {
			return vector.remove(vector.size() - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new NoMoreElementException();
		}
	}

	public void push(T pElement) {
		if (!vector.contains(pElement)) {
			vector.add(pElement);
		}
	}

	public T peek() throws NoMoreElementException {
		try {
			return vector.lastElement();
		} catch (NoSuchElementException e) {
			throw new NoMoreElementException();
		}
	}

	public long size() {
		return vector.size();
	}

	public String toString() {
		return vector.toString();
	}

	public void sort(Comparator<T> pComparator) {
		vector.sort(pComparator);
	}
}
