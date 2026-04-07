package com.project.sport.dto.response;

import org.springframework.http.ResponseCookie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtCookiesResponse {
	private ResponseCookie jwtCookies;
	private ResponseCookie jwtRefreshCookies;
}
