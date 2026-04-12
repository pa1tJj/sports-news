package com.project.sport.dto.response;

import org.springframework.http.ResponseCookie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtCookiesResponse {
	private ResponseCookie jwtCookies;
	private ResponseCookie jwtRefreshCookies;
}
