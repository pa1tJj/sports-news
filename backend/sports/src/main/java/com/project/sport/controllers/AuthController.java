package com.project.sport.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sport.dto.request.user.UserLoginRequest;
import com.project.sport.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> getLogin(@Valid @RequestBody UserLoginRequest userLoginRequest) {
		try {
			ResponseCookie cookie = authService.getJwtToken(userLoginRequest);
			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("login success");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
