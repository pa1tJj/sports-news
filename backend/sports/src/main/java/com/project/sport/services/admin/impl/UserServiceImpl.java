package com.project.sport.services.admin.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.sport.converters.UserConverter;
import com.project.sport.customexceptions.ResourceAlreadyExistsException;
import com.project.sport.dto.request.user.UserSaveRequest;
import com.project.sport.dto.request.user.CustomerSearchRequest;
import com.project.sport.dto.response.user.CustomerListResponse;
import com.project.sport.dto.response.user.UserFormResponse;
import com.project.sport.dto.response.user.UserListResponse;
import com.project.sport.dto.response.user.UserOptionsForm;
import com.project.sport.entities.RoleEntity;
import com.project.sport.entities.UserEntity;
import com.project.sport.enums.StaffRole;
import com.project.sport.enums.UserStatus;
import com.project.sport.repositories.RoleRepository;
import com.project.sport.repositories.UserRepository;
import com.project.sport.services.admin.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final UserConverter userConverter;
	private final RoleRepository roleRepository;
	
	@Override
	public Map<Long, String> getUsers() {
		return userRepository.findAll()
				             .stream()
				             .collect(Collectors.toMap(
				                  UserEntity::getId, 
				                  UserEntity::getFullname
				             ));
	}

	@Override
	public List<UserListResponse> getUsersByStaffRole(Pageable pageable) {
		Page<UserEntity> users = userRepository.findUsersExcludeRoles(
				List.of(StaffRole.ADMIN.name(), StaffRole.MANAGER.name(), StaffRole.STAFF.name()), pageable);
		return users.stream().map(userConverter::toUserListResponse).collect(Collectors.toList());
	}

	@Override
	public UserFormResponse getUserFormResponse(Long id) {
		return userConverter.toUserFormResponse(userRepository.findById(id).orElseThrow());
	}

	@Override
	public void saveUser(UserSaveRequest userSaveRequest) {
		if(userRepository.existsByUsernameIgnoreCase(userSaveRequest.getUsername())) {
			throw new ResourceAlreadyExistsException("Username already exists");
		}
		Set<RoleEntity> roles = roleRepository.findByCode(userSaveRequest.getRole());
		userRepository.save(userConverter.toUserEntity(userSaveRequest, roles));
	}

	@Override
	public void changePassword() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUsers(List<Long> ids) {
		userRepository.deleteUserRole(ids);
		userRepository.deleteByIdIn(ids);
	}

	@Override
	public Integer totalPage() {
		Double total = (double) (userRepository.count() / 2);
		return (int) Math.ceil(total);
	}

	@Override
	public UserOptionsForm getUserOptionForm() {
		UserOptionsForm userOptionsForm = new UserOptionsForm();
		userOptionsForm.setRoles(StaffRole.getStaffRole());
		userOptionsForm.setStatus(UserStatus.getUserStatus());
		return userOptionsForm;
	}

	@Override
	public void checkUsernameExists(String username) {
		if(userRepository.existsByUsernameIgnoreCase(username)) {
			throw new ResourceAlreadyExistsException("Username already exists");
		}
	}

	@Override
	public List<CustomerListResponse> findUsersByRoleCustomer(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
		Page<UserEntity> users = userRepository.findCustomerUsers(customerSearchRequest, pageable, totalPageCustomer());
		return users.stream().map(userConverter::toCustomerListResponse).collect(Collectors.toList());
	}

	@Override
	public Integer totalPageCustomer() {
		double total = (double)userRepository.countByRoles_Code("CUSTOMER") / 5;
		return (int) Math.ceil(total);
	}

}
