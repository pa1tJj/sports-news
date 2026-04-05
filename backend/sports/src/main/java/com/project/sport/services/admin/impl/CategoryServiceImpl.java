package com.project.sport.services.admin.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.sport.converters.CategoryConverter;
import com.project.sport.dto.request.category.CategoryRequest;
import com.project.sport.dto.response.category.CategoryResponse;
import com.project.sport.entities.CategoryEntity;
import com.project.sport.repositories.CategoryRepository;
import com.project.sport.services.admin.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;
	private final CategoryConverter categoryConverter;

	@Override
	public Map<Long, String> categoriesName() {
		return categoryRepository.findAll().stream()
				.collect(Collectors.toMap(CategoryEntity::getId, CategoryEntity::getName));
	}

	@Override
	public List<CategoryResponse> getListCategory() {
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		return categoryEntities.stream().map(categoryConverter::toCategoryResponse).collect(Collectors.toList());
	}

	@Override
	public void saveCategory(CategoryRequest categoryRequest) {
		categoryRepository.save(categoryConverter.toCategoryEntity(categoryRequest));
	}

	@Override
	public void deleteCategory(List<Long> ids) {
		categoryRepository.deleteCategoryNews(ids);
		categoryRepository.deleteByIdIn(ids);
	}

	@Override
	public CategoryResponse getCategoryDetail(Long id) {
		return categoryConverter
				.toCategoryResponse(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException()));
	}

}
