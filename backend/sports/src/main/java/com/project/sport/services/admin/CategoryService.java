package com.project.sport.services.admin;

import java.util.List;
import java.util.Map;

import com.project.sport.dto.request.category.CategoryRequest;
import com.project.sport.dto.response.category.CategoryResponse;

public interface CategoryService {
	public Map<Long, String> categoriesName();
	public List<CategoryResponse> getListCategory();
	public void saveCategory(CategoryRequest categoryRequest);
	public void deleteCategory(List<Long> ids);
	public CategoryResponse getCategoryDetail(Long id);
}
