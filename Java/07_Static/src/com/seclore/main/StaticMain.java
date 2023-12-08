package com.seclore.main;

import com.seclore.pojo.MyClass;

public class StaticMain {
	public static void main(String[] args) {
		MyClass.staticDisplay();
		MyClass.staticDisplay();

		MyClass obj1 = new MyClass();
		MyClass obj2 = new MyClass();
		obj1.display();
		obj2.display();
	}
}
