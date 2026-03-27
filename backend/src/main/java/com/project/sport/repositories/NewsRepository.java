package com.project.sport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sport.entities.NewsEntity;
import com.project.sport.repositories.custom.NewsRepositoryCustom;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>, NewsRepositoryCustom{
	public void deleteByIdIn(List<Long> ids);
}
