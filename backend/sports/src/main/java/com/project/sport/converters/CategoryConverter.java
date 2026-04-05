package com.project.sport.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.sport.dto.request.category.CategoryRequest;
import com.project.sport.dto.response.category.CategoryResponse;
import com.project.sport.entities.CategoryEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
	private final ModelMapper mapper;
	
	public CategoryResponse toCategoryResponse(CategoryEntity categoryEntity) {
		return mapper.map(categoryEntity, CategoryResponse.class);
	}
	
	public CategoryEntity toCategoryEntity(CategoryRequest categoryRequest) {
		return mapper.map(categoryRequest, CategoryEntity.class);
	}
}
