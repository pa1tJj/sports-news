package com.project.sport.repositories.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.sport.dto.request.user.CustomerSearchRequest;
import com.project.sport.entities.UserEntity;

public interface UserRepositoryCustom {
	Page<UserEntity> findCustomerUsers(CustomerSearchRequest customerSearchRequest, Pageable pageable, Integer totalElement);
}
