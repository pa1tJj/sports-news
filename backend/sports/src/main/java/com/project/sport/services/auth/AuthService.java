package com.project.sport.services.auth;

import com.project.sport.dto.request.user.UserLoginRequest;
import com.project.sport.dto.response.JwtCookiesResponse;

public interface AuthService {
	JwtCookiesResponse getJwtCookies(UserLoginRequest userLoginRequest);
	JwtCookiesResponse getCookiesByRefreshCokies(String jwtRefreshCookies);
	JwtCookiesResponse getLogout(String jwtRefreshCookies);
}
