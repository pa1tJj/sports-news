package com.project.sport.dto.request.news;

import java.util.Set;

import com.project.sport.entities.BaseEntity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsSaveRequest extends BaseEntity{
	private static final long serialVersionUID = -7031605866587435837L;

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
	private Set<Long> categories;
	
	@NotEmpty(message = "Please fill out this field")
	private Set<Long> tags;
	
	private Boolean isFeatured;
	
}
