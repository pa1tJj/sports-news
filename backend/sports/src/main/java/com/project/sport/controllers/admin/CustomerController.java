package com.project.sport.controllers.admin;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sport.dto.request.user.CustomerSearchRequest;
import com.project.sport.dto.response.DataResponses;
import com.project.sport.services.admin.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class CustomerController {
	private final UserService userService;
	
	@GetMapping("/customers")
	public ResponseEntity<DataResponses> getUsersByRoleCustomer(CustomerSearchRequest request) {
		try {
			var customers = userService.findUsersByRoleCustomer(request, PageRequest.of(request.getPage() - 1, request.getPageSize()));
			var totalPage = userService.totalPageCustomer();
			var optionForms = userService.getUserOptionForm();
			return ResponseEntity.ok(DataResponses.of(customers, optionForms, totalPage));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
