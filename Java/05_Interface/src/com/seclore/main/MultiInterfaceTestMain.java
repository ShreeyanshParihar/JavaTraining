package com.seclore.main;

public class MultiInterfaceTestMain {

	public static void main(String[] args) {
		var mi = new MultiInterface();
		mi.method1(1);
	}

}

interface Interface1 {
	public void method1(int input);
}

interface Interface2 {
	public void method1(int input);
}

class MultiInterface implements Interface1, Interface2 {
	@Override
	public void method1(int input) {
	}
}
