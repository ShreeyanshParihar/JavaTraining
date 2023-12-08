package com.seclore.main;

import com.seclore.util.Calculator;

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();

		calculator.acceptNumbers();
		calculator.calculateResult();
		calculator.displayResult();
	}

}
