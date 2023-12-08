package com.seclore.main;

import com.seclore.pojo.MyClass;

public class GenericMain {

	public static void main(String[] args) {
		MyClass<String> myClassString = new MyClass<>("Divy");
		myClassString.display();

		MyClass<Boolean> myClassBoolean = new MyClass<>(true);
		myClassBoolean.display();

		MyClass<Integer> myClassInteger = new MyClass<>(10);
		myClassInteger.display();
	}

}
