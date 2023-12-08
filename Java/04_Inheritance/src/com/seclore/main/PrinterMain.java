package com.seclore.main;

import java.util.Scanner;

import com.seclore.pojo.Canon110;
import com.seclore.pojo.CustomPageSize;
import com.seclore.pojo.HP3536;
import com.seclore.pojo.Printer;

public class PrinterMain {
	public static void main(String[] args) {
		Printer printer;
		Scanner scanner = new Scanner(System.in);
		int printerType;

		System.out.println("Select Printer");
		System.out.println("1. Laser Printer - HP3536");
		System.out.println("2. Dot Matrix Printer - Canon110");
		System.out.print("Enter choice: ");
		printerType = scanner.nextInt();
		scanner.nextLine();

		printer = printerFactory(printerType);
		printer.print();

		if(printer instanceof CustomPageSize) {
			((CustomPageSize) printer).setPageSize();
		}
		
		scanner.close();
	}

	private static Printer printerFactory(int printerType) {
		switch (printerType) {
		case 1:
			return new HP3536();
		case 2:
			return new Canon110();
		default:
			return null;
		}
	}
}
