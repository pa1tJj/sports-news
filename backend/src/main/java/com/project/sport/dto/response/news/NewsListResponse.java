package com.project.sport.dto.response.news;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsListResponse {
	private Long id;
	private String title;
	private String categoryName;
	private String status;
	private Integer views;
	private String createdDate;
}
