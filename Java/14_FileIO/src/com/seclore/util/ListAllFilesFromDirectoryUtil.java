package com.seclore.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

public class ListAllFilesFromDirectoryUtil {
	private File directory;

	public ListAllFilesFromDirectoryUtil(File directory) {
		super();
		this.directory = directory;
	}

	public List<File> printFilesFromDirectory() {
		if (!directory.exists() || !directory.isDirectory()) {
			System.out.println("Invalid directory path.");
			return null;
		}

		System.out.println("All files in \"" + directory.getAbsolutePath() + "\" are:");
		File[] files = directory.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.isFile() && file.getName().endsWith(".exe");
			}
		});

		return Arrays.asList(files);
	}
}
