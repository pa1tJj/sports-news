package com.project.sport.enums;

import java.util.HashMap;
import java.util.Map;

public enum NewsStatus {
	DRAFT("Bản nháp"),
	PUBLISHED("Đã đăng"),
	HIDDEN("Đã ẩn");
	
	private String name;
	private NewsStatus(String name) {
		this.name = name;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Map<String, String> newsStatus() {
		Map<String, String> status = new HashMap<>();
		for(NewsStatus it : NewsStatus.values()) {
			status.put(it.name(), it.name);
		}
		return status;
	}
}
