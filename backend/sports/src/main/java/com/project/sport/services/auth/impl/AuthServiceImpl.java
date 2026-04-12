package com.project.sport.services.auth.impl;

import java.util.UUID;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.sport.dto.request.user.UserLoginRequest;
import com.project.sport.dto.response.JwtCookiesResponse;
import com.project.sport.entities.UserEntity;
import com.project.sport.models.MyUserDetails;
import com.project.sport.repositories.UserRepository;
import com.project.sport.services.auth.AuthService;
import com.project.sport.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final RefreshTokenServiceRedisImpl refreshTokenServiceRedisImpl;
	private final UserRepository userRepository;

	@Override
	public JwtCookiesResponse getJwtCookies(UserLoginRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				request.getUsername(), request.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
		
		String token = UUID.randomUUID().toString();
		ResponseCookie accessCookies = jwtUtils.generateJwtCookies(userDetails);
		ResponseCookie refreshCookies = jwtUtils.generateRefreshJwtCookies(token, "/api/auth");
		
		refreshTokenServiceRedisImpl.saveRefreshTokenToRedis(token, userDetails.getUsername());
		return new JwtCookiesResponse(accessCookies, refreshCookies);
	}

	@Override
	public JwtCookiesResponse getCookiesByRefreshCokies(String jwtRefreshCookies) {
		String username = refreshTokenServiceRedisImpl.exchangeTokenForUsername(jwtRefreshCookies);
		UserEntity user = userRepository.findByUsername(username);
		
		String refreshToken = UUID.randomUUID().toString();
		ResponseCookie accessCookies = jwtUtils.generateJwtCookies(user);
		ResponseCookie refreshCookies = jwtUtils.generateRefreshJwtCookies(refreshToken, "/api/auth");
		
		refreshTokenServiceRedisImpl.saveRefreshTokenToRedis(refreshToken, user.getUsername());
		return new JwtCookiesResponse(accessCookies, refreshCookies);
	}


	@Override
	public JwtCookiesResponse getLogout(String jwtRefreshCookies) {
		refreshTokenServiceRedisImpl.deleteKeyInRedis("refresh-token:" + jwtRefreshCookies);
		ResponseCookie accessCookies = jwtUtils.getCleanJwtCookie();
		ResponseCookie refreshCookies = jwtUtils.getCleanRefreshJwtCookie();
		return new JwtCookiesResponse(accessCookies, refreshCookies);
	}
}
