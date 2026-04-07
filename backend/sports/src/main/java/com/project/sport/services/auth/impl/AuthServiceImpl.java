package com.project.sport.services.auth.impl;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.sport.customexceptions.TokenRefreshException;
import com.project.sport.dto.request.user.UserLoginRequest;
import com.project.sport.dto.response.JwtCookiesResponse;
import com.project.sport.entities.RefreshTokenEntity;
import com.project.sport.entities.UserEntity;
import com.project.sport.models.MyUserDetails;
import com.project.sport.repositories.RefreshTokenRepository;
import com.project.sport.services.auth.AuthService;
import com.project.sport.services.auth.RefreshTokenService;
import com.project.sport.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final RefreshTokenService refreshTokenService;
	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	public JwtCookiesResponse getJwtCookies(UserLoginRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				request.getUsername(), request.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
		JwtCookiesResponse jwtCookieResponse = new JwtCookiesResponse();
		jwtCookieResponse.setJwtCookies(jwtUtils.generateJwtCookies(userDetails));
		RefreshTokenEntity refreshTokenEntity = refreshTokenService.createRefreshToken(userDetails.getId());
		jwtCookieResponse.setJwtRefreshCookies(
				jwtUtils.generateRefreshJwtCookies(refreshTokenEntity.getToken(), "/api/auth/refresh-token"));
		return jwtCookieResponse;
	}

	@Override
	public JwtCookiesResponse getCookiesByRefreshCokies(HttpServletRequest request) {
		String refreshToken = jwtUtils.getRefreshJwtFromCookies(request);
		if (refreshToken != null && !refreshToken.isEmpty()) {
			return null;
		}
		RefreshTokenEntity tokenEntity = refreshTokenService.findByToken(refreshToken)
				.orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
		if (refreshTokenService.verifyExpiration(tokenEntity) != null) {
			UserEntity user = tokenEntity.getUser();
			refreshTokenRepository.delete(tokenEntity);
			refreshTokenRepository.flush();
			RefreshTokenEntity refreshTokenEntity = refreshTokenService.createRefreshToken(user.getId());
			JwtCookiesResponse jwtCookieResponse = new JwtCookiesResponse();
			jwtCookieResponse.setJwtCookies(jwtUtils.generateJwtCookies(user));
			jwtCookieResponse.setJwtRefreshCookies(
					jwtUtils.generateRefreshJwtCookies(refreshTokenEntity.getToken(), "/api/auth/refresh-token"));
			return jwtCookieResponse;
		}
		return null;
	}

	@Override
	public JwtCookiesResponse getLogout() {
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (userDetails.toString() != "anonymousUser") {
			refreshTokenService.deleteByUserId(userDetails.getId());
		}
		ResponseCookie jwtCookies = jwtUtils.getCleanJwtCookie();
		ResponseCookie jwtRefreshCookies = jwtUtils.getCleanRefreshJwtCookie();
		JwtCookiesResponse jwtCookieResponse = new JwtCookiesResponse();
		jwtCookieResponse.setJwtCookies(jwtCookies);
		jwtCookieResponse.setJwtRefreshCookies(jwtRefreshCookies);
		return jwtCookieResponse;
	}
}
