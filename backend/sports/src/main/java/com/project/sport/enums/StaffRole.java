package com.project.sport.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum StaffRole {
	STAFF("Nhân viên"),
	ADMIN("Quản trị viên"),
	MANAGER("Quản lý");
	
	private StaffRole(String name) {
	}

	public static Map<String, String> getStaffRole() {
		return Arrays.stream(StaffRole.values()).collect(Collectors.toMap(StaffRole::name, StaffRole::name));
	}
}
