package com.project.sport.dto.request.tag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagRequest {
	private Long id;
	private String name;
	private String slug;
}
