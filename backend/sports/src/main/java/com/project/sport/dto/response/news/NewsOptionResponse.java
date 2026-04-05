package com.project.sport.dto.response.news;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsOptionResponse {
	private Map<Long, String> categories;
	private Map<Long, String> tags;
	private Map<String, String> status;
}
