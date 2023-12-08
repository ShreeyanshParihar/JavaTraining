package com.seclore.pojo;

import java.util.Date;

public class BookIssue {
	private int code;
	private BookCopy bookCopy;
	private Member member;
	private Date dateOfIssue;
	private Date dateOfReturn;
	private String status;

	public BookIssue(int code, BookCopy bookCopy, Member member, Date dateOfIssue, Date dateOfReturn, String status) {
		super();
		this.code = code;
		this.bookCopy = bookCopy;
		this.member = member;
		this.dateOfIssue = dateOfIssue;
		this.dateOfReturn = dateOfReturn;
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBook(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Date getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return "BookIssue [code=" + code + ", bookCopyCode=" + bookCopy.getCode() + ", memberCode=" + member.getCode()
				+ ", dateOfIssue=" + dateOfIssue + ", dateOfReturn=" + dateOfReturn + ", status=" + status + "]";
	}
}