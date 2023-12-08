package com.seclore.main;

import com.seclore.exceptions.InvalidProductPriceException;
import com.seclore.pojo.Product;

public class ProductMain {

	public static void main(String[] args) {
		try {
			Product product = new Product(1, "Pen", 1000);
			System.out.println(product);
		} catch (InvalidProductPriceException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------------");
		
		Product product = new Product();
		product.setId(10);
		product.setName("Pencil");
		try {
			product.setPrice(100);
			System.out.println(product);
		} catch (InvalidProductPriceException e) {
			System.out.println(e.getMessage());
		}
	}

}
