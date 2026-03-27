package com.project.sport.dto.response.news;

import java.util.Map;

import com.project.sport.entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDetailResponse extends BaseEntity{
	private Long id;
	private String title;
	private String summary;
	private String slug;
	private String status;
	private Map<Long, String> categories;
	private String content;
	private String thumbnailPreview;
	private Map<Long, String> tags;
	private Boolean isFeatured;
}
