package com.seclore.pojo;

public class BookCopy {
	private int code;
	private Book book;
	private boolean issuable;

	public BookCopy(int code, Book book, boolean issuable) {
		this.code = code;
		this.book = book;
		this.issuable = issuable;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean isIssuable() {
		return issuable;
	}

	public void setIssuable(boolean issuable) {
		this.issuable = issuable;
	}

	public String toString() {
		return "BookCopy [code=" + code + ", bookSerialNumber=" + book.getSerialNumber() + ", issuable=" + issuable
				+ "]";
	}

}