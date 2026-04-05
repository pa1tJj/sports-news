package com.project.sport.dto.request.user;

import com.project.sport.dto.AbstractDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchRequest extends AbstractDTO{
	private String username;
	private String phone;
	private String email;
	private String status;
}
