package com.project.sport.dto.response.news;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsFilterDataResponse {
	private String title;
	private Map<String, String> sortBy;
	private Map<Long, String> categoryName;
	private Map<String, String> status;
	private Map<Long, String> users;
	private Map<String, String> dateOptions;
	private Map<Long, String> tags;
}
