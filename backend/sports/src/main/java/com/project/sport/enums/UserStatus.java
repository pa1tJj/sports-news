package com.project.sport.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum UserStatus {
	ACTIVE(""),
	DISABLE(""),
	DELETED("");
	private UserStatus(String name) {}
	
	public static Map<String, String> getUserStatus() {
		return Arrays.stream(UserStatus.values()).collect(Collectors.toMap(UserStatus::name, UserStatus::name));
	}
}
