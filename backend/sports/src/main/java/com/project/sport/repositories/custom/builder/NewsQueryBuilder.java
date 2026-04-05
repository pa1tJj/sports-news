package com.project.sport.repositories.custom.builder;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.project.sport.converters.NewsConverter;
import com.project.sport.dto.request.news.NewsSearchRequest;
import com.project.sport.utils.QueryUtils;
import com.project.sport.utils.StringUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewsQueryBuilder {
	private final NewsConverter newsConverter;
	private final QueryUtils queryUtils;
	private static final String FIELD_TITLE = "news.title";
	private static final String FIELD_STATUS = "news.status";
	private static final String USER_ID = "news.user_id";

	public void joinTable(NewsSearchRequest newsRequest, StringBuilder query) {
		if (newsRequest.getCategoryId() != null) {
			query.append(" JOIN news_category ON news.id = news_category.news_id JOIN categories ON categories.id = news_category.category_id ");
		}
	}
	
	public void queryBasic(NewsSearchRequest newsRequest, StringBuilder query, Map<String, Object> params) {
		queryUtils.likeConditions(query, params, FIELD_TITLE, newsRequest.getTitle());
		queryUtils.likeConditions(query, params, FIELD_STATUS, newsRequest.getStatus());
		queryUtils.equalConditions(query, params, USER_ID, newsRequest.getUserId());
	}
	
	public void buildDateConditions(NewsSearchRequest newsRequest, StringBuilder query, Map<String, Object> params) {
		if (StringUtils.checkString(newsRequest.getDate()) && (!newsRequest.getDate().contains("TODAY") || !newsRequest.getDate().contains("YESTERDAY")) ) {
			queryUtils.betweenConditions(query, params, "createddate", newsConverter.stringToDate(newsRequest.getDate()), LocalDateTime.now());
		} else {
			queryUtils.equalConditions(query, params, "createddate", newsConverter.stringToDate(newsRequest.getDate()));
		}
	}
	
	public void buildCategoryCondition(NewsSearchRequest newsRequest, StringBuilder query, Map<String, Object> params) {
		if(newsRequest.getCategoryId() != null) {
			query.append(" AND categories.id =:category_id ");
			params.put("category_id", newsRequest.getCategoryId());
		}
	}
	
	public void buildSortByCondition(NewsSearchRequest newsRequest, StringBuilder query, Map<String, Object> params) {
		if (StringUtils.checkString(newsRequest.getSortBy())) {
			if (newsRequest.getSortBy().equals("VIEWS") || newsRequest.getSortBy().equals("PRIORITY")) {
				query.append(" ORDER BY news.").append(newsRequest.getSortBy().toLowerCase()).append(" DESC");
			}
		}
	}

	public void queryComplicated(NewsSearchRequest newsRequest, StringBuilder query, Map<String, Object> params) {
		buildCategoryCondition(newsRequest, query, params);
		buildDateConditions(newsRequest, query, params);
		buildSortByCondition(newsRequest, query, params);	
	}

}
