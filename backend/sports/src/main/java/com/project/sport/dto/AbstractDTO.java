package com.project.sport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDTO {
	private int pageSize = 5;
	private int page = 1;
}
