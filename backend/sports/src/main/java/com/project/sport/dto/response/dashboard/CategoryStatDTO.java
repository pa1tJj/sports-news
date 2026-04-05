package com.project.sport.dto.response.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryStatDTO {
	private Long id;
	private String name;
	private Long total;
}
