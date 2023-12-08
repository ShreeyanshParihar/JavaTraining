package com.seclore.main;

import java.io.File;
import java.util.Scanner;

import com.seclore.util.BufferedWriterUtil;
import com.seclore.util.PrintFileMetadataUtil;

public class BufferedWriterMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BufferedWriterUtil bufferedWriterUtil = new BufferedWriterUtil();
		PrintFileMetadataUtil fileMetadataUtil;

		String path;
		File file;
		StringBuffer data = new StringBuffer();

		System.out.print("Enter file path: ");
		path = scanner.nextLine();
		file = new File(path);

		System.out.println("Enter data for file: ");
		while (scanner.hasNextLine()) {
			data.append(scanner.nextLine() + "\n");
		}

		System.out.println();
		System.out.println("--------------");
		System.out.println(" File Details");
		System.out.println("--------------");
		fileMetadataUtil = new PrintFileMetadataUtil(file);
		fileMetadataUtil.printMetadataOfFile();

		bufferedWriterUtil.writeFile(file, data.toString().toCharArray(), true);
		System.out.println();
		scanner.close();
	}

}
