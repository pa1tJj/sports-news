package com.project.sport.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum SortBy {
	PRIORITY("Đề xuất"),
	VIEWS("Lượng xem (Cao đến thấp)");
	
	private String name;
	private SortBy(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Map<String, String> sortBy() {
		return Arrays.stream(values()).collect(Collectors.toMap(SortBy::name, SortBy::getName));
	}
 }
