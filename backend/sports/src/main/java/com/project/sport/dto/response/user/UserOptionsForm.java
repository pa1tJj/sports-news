package com.project.sport.dto.response.user;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOptionsForm {
	private Map<String, String> roles;
	private Map<String, String> status;
}
