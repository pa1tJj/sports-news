package com.project.sport.services.admin;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.project.sport.dto.request.user.UserSaveRequest;
import com.project.sport.dto.request.user.CustomerSearchRequest;
import com.project.sport.dto.response.user.CustomerListResponse;
import com.project.sport.dto.response.user.UserFormResponse;
import com.project.sport.dto.response.user.UserListResponse;
import com.project.sport.dto.response.user.UserOptionsForm;

public interface UserService {
	public Map<Long, String> getUsers();
	List<UserListResponse> getUsersByStaffRole(Pageable pageable);
	UserFormResponse getUserFormResponse(Long id);
	void saveUser(UserSaveRequest request);
	void changePassword();
	void deleteUsers(List<Long> ids);
	Integer totalPage();
	UserOptionsForm getUserOptionForm();
	void checkUsernameExists(String username);
	List<CustomerListResponse> findUsersByRoleCustomer(CustomerSearchRequest customerSearchRequest, Pageable pageable);
	Integer totalPageCustomer();
}
