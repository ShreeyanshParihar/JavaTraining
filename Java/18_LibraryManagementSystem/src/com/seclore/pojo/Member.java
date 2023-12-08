package com.seclore.pojo;

public class Member {
	private int code;
	private String name;
	private String type;

	public Member(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public Member(int code, String name, String type) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Member [code=" + code + ", name=" + name + ", type=" + type + "]";
	}

	public int getIssueLimit() {
		return this.type == "student" ? 3 : 25;
	}

	public int getIssuePeriod() {
		return this.type == "student" ? 10 : 90;
	}

}
