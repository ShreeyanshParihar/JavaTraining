package com.seclore.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {
	private FileWriter writer;

	public boolean writeFile(File file, char[] data, boolean append) {
		try {
			writer = new FileWriter(file, append);
			writer.write(data);
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
}
