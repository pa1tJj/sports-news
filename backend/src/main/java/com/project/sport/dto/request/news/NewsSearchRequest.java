package com.project.sport.dto.request.news;

import java.util.Set;

import com.project.sport.dto.AbstractDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsSearchRequest extends AbstractDTO{
	private String title;
	private String sortBy;
	private Long categoryId;
	private String status;
	private Long userId;
	private String date;
	private Set<String> tags;
}
