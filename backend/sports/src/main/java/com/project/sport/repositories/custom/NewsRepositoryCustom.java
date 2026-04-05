package com.project.sport.repositories.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.sport.dto.request.news.NewsSearchRequest;
import com.project.sport.entities.NewsEntity;

public interface NewsRepositoryCustom {
	public Page<NewsEntity> getNews(NewsSearchRequest newsRequest, Pageable pageable, Integer totalElement);
}
