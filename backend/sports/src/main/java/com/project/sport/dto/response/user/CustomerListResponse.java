package com.project.sport.dto.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerListResponse {

	private Long id;
	private String username;
	private String email;
	private String phone;
	private String status;
}
