package com.project.sport.repositories.custom.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.project.sport.dto.request.user.CustomerSearchRequest;
import com.project.sport.entities.UserEntity;
import com.project.sport.repositories.custom.UserRepositoryCustom;
import com.project.sport.utils.QueryUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	private final QueryUtils queryUtils;
	@PersistenceContext
	private EntityManager entityManager;

	private static final String COL_USERNAME = "users.username";
	private static final String COL_PHONE = "users.phone";
	private static final String COL_EMAIL = "users.email";
	private static final String COL_STATUS = "users.status";

	@Override
	public Page<UserEntity> findCustomerUsers(CustomerSearchRequest customerSearchRequest, Pageable pageable,
			Integer totalElement) {
		StringBuilder sql = new StringBuilder("""
				SELECT users.*
				FROM users 
				INNER JOIN user_role ON users.id = user_role.user_id 
				INNER JOIN roles ON roles.id = user_role.role_id
				WHERE roles.code = 'CUSTOMER' """);
		Map<String, Object> param = new HashMap<String, Object>();
		queryUtils.likeConditions(sql, param, COL_USERNAME, customerSearchRequest.getUsername());
		queryUtils.likeConditions(sql, param, COL_PHONE, customerSearchRequest.getPhone());
		queryUtils.likeConditions(sql, param, COL_EMAIL, customerSearchRequest.getEmail());
		queryUtils.likeConditions(sql, param, COL_STATUS, customerSearchRequest.getStatus());

		Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
		query.setFirstResult(Math.toIntExact(pageable.getOffset()));
		query.setMaxResults(pageable.getPageSize());
		return new PageImpl<UserEntity>(query.getResultList(), pageable, totalElement);
	}

}
