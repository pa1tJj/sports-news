package com.project.sport.dto.response.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopPostDTO {
	private String title;
	private Integer views;
	private String status;
}
