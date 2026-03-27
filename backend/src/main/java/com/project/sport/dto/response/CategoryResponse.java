package com.project.sport.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
	private Long id;
	private String name;
	private String slug;
	private String description;
}
