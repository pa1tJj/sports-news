package com.project.sport.converters;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.sport.dto.request.user.UserSaveRequest;
import com.project.sport.dto.response.user.CustomerListResponse;
import com.project.sport.dto.response.user.UserFormResponse;
import com.project.sport.dto.response.user.UserListResponse;
import com.project.sport.entities.RoleEntity;
import com.project.sport.entities.UserEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserConverter {
	private final ModelMapper mapper;
	
	public UserListResponse toUserListResponse(UserEntity userEntity) {
		UserListResponse userListResponse = mapper.map(userEntity, UserListResponse.class);
		userListResponse.setRole(userEntity.getRoles().stream().map(RoleEntity::getCode).collect(Collectors.joining(",")));
		return userListResponse;
	}
	
	public UserFormResponse toUserFormResponse(UserEntity userEntity) {
		UserFormResponse userFormResponse = mapper.map(userEntity, UserFormResponse.class);
		userFormResponse.setRole(userEntity.getRoles().stream().map(RoleEntity::getCode).collect(Collectors.joining(",")));
		return userFormResponse; 
	}
	
	public UserEntity toUserEntity(UserSaveRequest userSaveRequest, Set<RoleEntity> roles) {
		UserEntity userEntity = mapper.map(userSaveRequest, UserEntity.class);
		userEntity.setRoles(roles);
		return userEntity;
	}
	
	public CustomerListResponse toCustomerListResponse(UserEntity userEntity) {
		return mapper.map(userEntity, CustomerListResponse.class);
	}
}
