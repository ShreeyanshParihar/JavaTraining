package com.seclore.pojo;

import com.seclore.annotations.JavaFileInfo;

@JavaFileInfo(author = "Divy", version = "1.0")
public class DemoClass {
	private String message = "Sample message.";

	@JavaFileInfo(version = "1.1")
	public String printMessage() {
		return message;
	}
}
