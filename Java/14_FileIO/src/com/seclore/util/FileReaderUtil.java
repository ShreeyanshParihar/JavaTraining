package com.seclore.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileReaderUtil {
	private Reader reader;
	private char[] data;

	public char[] readFile(File file) {
		try {
			reader = new FileReader(file);
			data = new char[(int) file.length()];
			reader.read(data);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file location.");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Can't read file.");
			System.out.println(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Failed to close stream.");
				System.out.println(e.getMessage());
			}
		}
		return data;
	}
}
