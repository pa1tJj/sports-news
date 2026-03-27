package com.project.sport.services.admin.impl;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.sport.entities.CategoryEntity;
import com.project.sport.repositories.CategoryRepository;
import com.project.sport.services.admin.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final CategoryRepository categoriesRepository;

	@Override
	public Map<Long, String> categoriesName() {
		return categoriesRepository.findAll()
				                   .stream()
				                   .collect(Collectors.toMap(
				                       CategoryEntity::getId, 
				                       CategoryEntity::getName
				                   ));
	}

}
