package com.project.sport.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataResponses {
	private List<?> list;
	private Object object;
	private int totalPage;
	
	public static DataResponses of(List<?> list, Object object, int totalPage) {
		return new DataResponses(list, object, totalPage);
	}
	
	public static DataResponses withList(List<?> list, int totalPage) {
		return new DataResponses(list, null, totalPage);
	}
}
