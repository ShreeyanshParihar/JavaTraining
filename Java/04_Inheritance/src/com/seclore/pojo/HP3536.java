package com.seclore.pojo;

public class HP3536 extends Printer implements CustomPageSize {
	public void print() {
		System.out.println("printing... (laser printer - HP3536)");
	}
	
	public void setPageSize() {
		System.out.println("setting custom page size... (laser printer - HP3536)");
	}
}
