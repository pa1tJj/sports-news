package com.project.sport.utils;

public class DataTypeUtils {

	public static Boolean checkStringDataType(Object object) {
		String data = object.getClass().getName();
		if (data.contains("String"))
			return true;
		return false;
	}

	public static Boolean checkNumericDataType(Object object) {
		if (object.getClass().getName().contains("Integer") || object.getClass().getName().contains("Long")
				|| object.getClass().getName().contains("Double"))
			return true;
		return false;
	}

	public static Boolean checkDateDataType(Object object) {
		if (object.getClass().getName().contains("java.time.LocalDateTime")
				|| object.getClass().getName().contains("java.time.LocalDate"))
			return true;
		return false;
	}
}
