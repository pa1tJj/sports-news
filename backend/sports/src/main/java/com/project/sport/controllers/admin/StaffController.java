package com.project.sport.controllers.admin;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.sport.dto.request.user.UserSaveRequest;
import com.project.sport.dto.response.DataResponses;
import com.project.sport.dto.response.user.UserFormResponse;
import com.project.sport.services.admin.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class StaffController {
	private final UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<DataResponses> getUsersByStafRole(@RequestParam(name = "page", defaultValue = "1") Integer page) {
		try {
			var staff = userService.getUsersByStaffRole(PageRequest.of(page - 1, 2));
			var totalPage = userService.totalPage();
			var optionForms = userService.getUserOptionForm();
			return ResponseEntity.ok(DataResponses.of(staff, optionForms, totalPage));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserFormResponse> getUserDetail(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(userService.getUserFormResponse(id));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody UserSaveRequest userSaveRequest) {
		userService.saveUser(userSaveRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<HttpStatus> updateUser(@Valid @RequestBody UserSaveRequest userSaveRequest) {
		try {
			userService.saveUser(userSaveRequest);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/users/{ids}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable List<Long> ids) {
		try {
			userService.deleteUsers(ids);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/users/exists")
	public void checkUsernameExists(@RequestParam("username") String username) {
		userService.checkUsernameExists(username);
	}
}
