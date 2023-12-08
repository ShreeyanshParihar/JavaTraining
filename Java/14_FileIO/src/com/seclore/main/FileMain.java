package com.seclore.main;

import java.io.File;

import com.seclore.util.PrintFileMetadataUtil;
import java.util.List;
import com.seclore.util.ListAllFilesFromDirectoryUtil;

public class FileMain {

	public static void main(String[] args) {
		File file = new File("details.txt");
		PrintFileMetadataUtil metadataUtil = new PrintFileMetadataUtil(file);
		metadataUtil.printMetadataOfFile();

		System.out.println();
		System.out.println();
		
		ListAllFilesFromDirectoryUtil pafdUtil = new ListAllFilesFromDirectoryUtil(new File("C:/Windows"));
		List<File> filesInWindows = pafdUtil.printFilesFromDirectory();
		for (File fileInWindows : filesInWindows) {
			System.out.println();
			metadataUtil = new PrintFileMetadataUtil(fileInWindows);
			metadataUtil.printMetadataOfFile();
		}
	}

}
