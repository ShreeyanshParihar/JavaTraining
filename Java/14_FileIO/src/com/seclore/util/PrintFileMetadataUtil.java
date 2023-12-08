package com.seclore.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PrintFileMetadataUtil {
	private File file;

	public PrintFileMetadataUtil(File file) {
		super();
		this.file = file;
	}

	public void printMetadataOfFile() {
		if (!file.exists()) {
			System.out.println("File does not exist.");
			return;
		}

		System.out.println("File Name: " + file.getName());
		System.out.println("Path: " + file.getAbsolutePath());
		try {
			System.out.println("Path: " + file.getCanonicalPath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Size: " + file.length() + " bytes");
		System.out.println("Last Modified: " + new Date(file.lastModified()));
	}

}
