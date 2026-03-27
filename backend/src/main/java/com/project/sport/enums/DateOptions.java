package com.project.sport.enums;

import java.util.HashMap;
import java.util.Map;

public enum DateOptions {
	TODAY("Hôm nay"),
	YESTERDAY("Hôm qua"),
	LAST_7_DAYS("7 ngày gần đây"),
	LAST_30_DAYS("30 ngày gần đây"),
	THIS_MONTH("Tháng này"),
	LAST_MONTH("Tháng trước");
	
	private String name;

	private DateOptions(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Map<String, String> dateOptions() {
		Map<String, String>  options = new HashMap<>();
		for(DateOptions it : DateOptions.values()) {
			options.put(it.name(), it.name);
		}
		return options;
	}
}
