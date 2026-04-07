package com.project.sport.services.auth;

import com.project.sport.dto.request.user.UserLoginRequest;
import com.project.sport.dto.response.JwtCookiesResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
	JwtCookiesResponse getJwtCookies(UserLoginRequest userLoginRequest);
	JwtCookiesResponse getCookiesByRefreshCokies(HttpServletRequest request);
	JwtCookiesResponse getLogout();
}
