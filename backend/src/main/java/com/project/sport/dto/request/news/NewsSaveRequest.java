package com.project.sport.dto.request.news;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsSaveRequest {
	private Long id;
	
	@NotEmpty(message = "Please fill out this field")
	private String title;
	
	@NotEmpty(message = "Please fill out this field")
	private String content;
	
	@NotEmpty(message = "Please fill out this field")
	private String slug;
	
	@NotEmpty(message = "Please fill out this field")
	private String status;
	
	@NotEmpty(message = "Please fill out this field")
	private String summary;
	
	@NotEmpty(message = "Please fill out this field")
	private Set<Long> category;
	
	@NotEmpty(message = "Please fill out this field")
	private Set<Long> tag;
	
	private Boolean isFeatured;
	
	private LocalDateTime dateTime = LocalDateTime.now();
}
