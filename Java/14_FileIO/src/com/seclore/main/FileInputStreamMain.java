package com.seclore.main;

import java.io.File;
import java.util.Scanner;

import com.seclore.util.FileInputStreamUtil;
import com.seclore.util.PrintFileMetadataUtil;

public class FileInputStreamMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		FileInputStreamUtil fileInputStreamUtil = new FileInputStreamUtil();
		PrintFileMetadataUtil fileMetadataUtil;

		String path;
		File file;

		System.out.print("Enter file path: ");
		path = scanner.nextLine();
		file = new File(path);

		System.out.println();
		System.out.println("--------------");
		System.out.println(" File Details");
		System.out.println("--------------");
		fileMetadataUtil = new PrintFileMetadataUtil(file);
		fileMetadataUtil.printMetadataOfFile();

		System.out.println();
		System.out.println("--------------");
		System.out.println(" File Content");
		System.out.println("--------------");
		for (byte b : fileInputStreamUtil.readFile(file)) {
			System.out.print((char) b);
		}
		System.out.println();
		scanner.close();
	}

}
