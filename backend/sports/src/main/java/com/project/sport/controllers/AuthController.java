package com.project.sport.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sport.dto.request.user.UserLoginRequest;
import com.project.sport.dto.response.JwtCookiesResponse;
import com.project.sport.services.auth.AuthService;
import com.project.sport.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> getLogin(@Valid @RequestBody UserLoginRequest userLoginRequest) {
		try {
			JwtCookiesResponse cookiesResponse = authService.getJwtCookies(userLoginRequest);
			return ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, cookiesResponse.getJwtCookies().toString())
					.header(HttpHeaders.SET_COOKIE, cookiesResponse.getJwtRefreshCookies().toString())
					.body("login success");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<?> getRefreshToken(HttpServletRequest request) {
		try {
			String jwtRefreshCookies = jwtUtils.getRefreshJwtFromCookies(request);
			JwtCookiesResponse cookiesResponse = authService.getCookiesByRefreshCokies(jwtRefreshCookies);
			return ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, cookiesResponse.getJwtCookies().toString())
					.header(HttpHeaders.SET_COOKIE, cookiesResponse.getJwtRefreshCookies().toString())
					.body("Token is refreshed successfully!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Refresh Token is empty!");
		}
	}
	
	@PostMapping("/signout")
	public ResponseEntity<?> getLogout(HttpServletRequest request) {
		try {
			String jwtRefreshCookies = jwtUtils.getRefreshJwtFromCookies(request);
			JwtCookiesResponse jwtCookie = authService.getLogout(jwtRefreshCookies);
			return ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, jwtCookie.getJwtCookies().toString())
					.header(HttpHeaders.SET_COOKIE, jwtCookie.getJwtRefreshCookies().toString())
					.body("Have been signed out");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
