package com.seclore.main;

import java.io.File;
import java.util.Scanner;

import com.seclore.util.FileOutputStreamUtil;
import com.seclore.util.PrintFileMetadataUtil;

public class FileOutputStreamMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		FileOutputStreamUtil fileOutputStreamUtil = new FileOutputStreamUtil();
		PrintFileMetadataUtil fileMetadataUtil;

		String path;
		File file;
		StringBuffer data = new StringBuffer();

		System.out.print("Enter file path: ");
		path = scanner.nextLine();
		file = new File(path);
		
		System.out.println("Enter data for file: ");
		while (scanner.hasNext()) {			
			data.append(scanner.nextLine() + "\n");
		}
		
		System.out.println(data);

		System.out.println();
		System.out.println("--------------");
		System.out.println(" File Details");
		System.out.println("--------------");
		fileMetadataUtil = new PrintFileMetadataUtil(file);
		fileMetadataUtil.printMetadataOfFile();

		fileOutputStreamUtil.writeFile(file, data.toString().getBytes(), false);
		System.out.println();
		scanner.close();
	}

}
