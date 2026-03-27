package com.project.sport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sport.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

}
