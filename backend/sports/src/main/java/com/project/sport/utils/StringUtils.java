package com.project.sport.utils;

public final class StringUtils {
	public static Boolean checkString(String data) {
		if(data != null && !data.equals("")) return true;
		return false;
	}
}
