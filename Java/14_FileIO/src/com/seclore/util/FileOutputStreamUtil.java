package com.seclore.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamUtil {
	private OutputStream outputStream;

	public boolean writeFile(File file, byte[] data, boolean append) {
		try {
			outputStream = new FileOutputStream(file, append);
			outputStream.write(data);
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
}
