package com.project.sport.repositories.custom.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.project.sport.dto.request.news.NewsSearchRequest;
import com.project.sport.entities.NewsEntity;
import com.project.sport.repositories.custom.NewsRepositoryCustom;
import com.project.sport.repositories.custom.builder.NewsQueryBuilder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class NewsRepositoryCustomImpl implements NewsRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;
	
	private final NewsQueryBuilder queryBuilder;
	private static final String TABLE_NEWS = "news";

	
	@Override
	public Page<NewsEntity> getNews(NewsSearchRequest newsRequest, Pageable pageable, Integer totalElements) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT news.* FROM " + TABLE_NEWS);
		queryBuilder.joinTable(newsRequest, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		Map<String, Object> params = new HashMap<>();
		queryBuilder.queryBasic(newsRequest, where, params);
		queryBuilder.queryComplicated(newsRequest, where, params);
		sql.append(where);
		Query query = entityManager.createNativeQuery(sql.toString(), NewsEntity.class);
		for(Map.Entry<String, Object> it : params.entrySet()) {
			query.setParameter(it.getKey(), it.getValue());
		}
		query.setFirstResult(Math.toIntExact(pageable.getOffset()));
		query.setMaxResults(pageable.getPageSize());
		return new PageImpl<NewsEntity>(query.getResultList(), pageable, totalElements);
	}

}
