package com.seclore.main;

import java.io.File;
import java.util.Scanner;

import com.seclore.util.BufferedReaderUtil;
import com.seclore.util.PrintFileMetadataUtil;

public class BufferedReaderMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BufferedReaderUtil bufferedReaderUtil = new BufferedReaderUtil();
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
		System.out.println(bufferedReaderUtil.readFile(file));
		System.out.println();
		scanner.close();
	}

}
