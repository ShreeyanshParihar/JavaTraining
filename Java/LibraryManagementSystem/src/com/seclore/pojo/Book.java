package com.seclore.pojo;

public class Book {
	private int serialNumber;
	private String name;
	private String author;

	public Book(String name, String author) {
		this.name = name;
		this.author = author;
	}

	public Book(int serialNumber, String name, String author) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.author = author;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [serialNumber=" + serialNumber + ", name=" + name + ", author=" + author + "]";
	}

}