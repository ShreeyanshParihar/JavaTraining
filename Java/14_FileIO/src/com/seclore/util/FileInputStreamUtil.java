package com.seclore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamUtil {
	private InputStream inputStream;
	private byte[] data;

	public byte[] readFile(File file) {
		try {
			inputStream = new FileInputStream(file);
			data = new byte[(int) file.length()];
			inputStream.read(data);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file location.");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Can't read file.");
			System.out.println(e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Failed to close stream.");
				System.out.println(e.getMessage());
			}
		}
		return data;
	}
}
