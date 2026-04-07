package com.project.sport.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
	private String username;
	private String password;
}
