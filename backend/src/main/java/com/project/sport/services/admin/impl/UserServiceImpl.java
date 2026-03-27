package com.project.sport.services.admin.impl;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.sport.entities.UserEntity;
import com.project.sport.repositories.UserRepository;
import com.project.sport.services.admin.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	@Override
	public Map<Long, String> getUsers() {
		return userRepository.findAll()
				             .stream()
				             .collect(Collectors.toMap(
				                  UserEntity::getId, 
				                  UserEntity::getFullname
				             ));
	}

}
