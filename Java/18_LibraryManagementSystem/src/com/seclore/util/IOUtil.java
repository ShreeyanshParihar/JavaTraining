package com.seclore.util;

import java.util.Scanner;

public class IOUtil {
    private Scanner scanner;

    public IOUtil() {
        scanner = new Scanner(System.in);
    }

    public boolean getBoolean(String text) {
        System.out.print(text);
        String choice = scanner.nextLine();
        return choice.equalsIgnoreCase("y") || choice == "";
    }

    public char getChar(String text) {
        System.out.print(text);
        char value = scanner.next().charAt(0);
        scanner.nextLine();
        return value;
    }

    public int getInt(String text) {
        System.out.print(text);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public double getDouble(String text) {
        System.out.print(text);
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public String getString(String text) {
        System.out.print(text);
        String value = scanner.nextLine();
        return value;
    }

    @Override
    protected void finalize() throws Throwable {
        scanner.close();
    }
}
