package com.project.sport.dto.response;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class DataResponses {
	private List<?> list;
	private Object object;
	private int totalPage;
}
