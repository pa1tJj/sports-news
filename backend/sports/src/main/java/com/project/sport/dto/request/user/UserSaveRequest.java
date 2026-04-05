package com.project.sport.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveRequest {
	private Long id;
	
	@NotEmpty(message = "Username is required")
	private String username;
	
	@NotEmpty(message = "Email is required")
	@Email(message = "Please enter a valid email address")
	private String email;
	
	@NotEmpty(message = "Role is required")
	private String role;
	
	@NotEmpty(message = "Status is required")
	private String status;
	
	@NotEmpty(message = "Phone is required")
	private String phone;
	
	@NotEmpty(message = "Password is required")
	private String password;
}
