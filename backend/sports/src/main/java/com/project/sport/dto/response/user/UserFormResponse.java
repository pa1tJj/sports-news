package com.project.sport.dto.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFormResponse {
	private Long id;
	private String username;
	private String email;
	private String role;
	private String status;
	private String phone;
}
