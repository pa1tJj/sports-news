package com.project.sport.utils;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class QueryUtils {
	
	public void likeConditions(StringBuilder query, Map<String, Object> params, String column, String value) {
		if(StringUtils.checkString(value)) {
			query.append(" AND ").append(column).append(" LIKE :").append(column);
			params.put(column, "%" + value + "%");
		}
	}
	
	public void equalConditions(StringBuilder query, Map<String, Object> params, String column, Object value) {
		if(value != null) {
			query.append(" AND ").append(column).append(" =:").append(column);
			params.put(column, value);
		}
	}

	public void betweenConditions(StringBuilder query, Map<String, Object> params, String column, Object start, Object end) {
		if(start != null && end != null) {
			query.append(" AND ").append(column).append(" BETWEEN ").append(":start").append(" AND ").append(":end");
			params.put("start", start);
			params.put("end", end);
		}
	}
	
}
