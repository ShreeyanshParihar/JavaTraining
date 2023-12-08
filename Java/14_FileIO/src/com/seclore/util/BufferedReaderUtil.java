package com.seclore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderUtil {
	private BufferedReader reader;
	private StringBuilder data;

	public String readFile(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
			data = new StringBuilder();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				data.append(line + "\n");
			}
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
		return data.toString();
	}
}
