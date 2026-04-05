package com.project.sport.dto.request.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
	private Long id;
	private String name;
	private String slug;
	private String description;
}
