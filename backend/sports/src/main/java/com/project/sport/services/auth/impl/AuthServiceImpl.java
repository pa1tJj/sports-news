package com.project.sport.services.auth.impl;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.sport.dto.request.user.UserLoginRequest;
import com.project.sport.dto.response.JwtCookiesResponse;
import com.project.sport.entities.RefreshTokenEntity;
import com.project.sport.models.MyUserDetails;
import com.project.sport.services.auth.AuthService;
import com.project.sport.services.auth.RefreshTokenService;
import com.project.sport.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final RefreshTokenService refreshTokenService;
	private final JwtCookiesResponse jwtCookieResponse;

	@Override
	public JwtCookiesResponse getJwtCookies(UserLoginRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
		jwtCookieResponse.setJwtCookies(jwtUtils.generateJwtCookies(userDetails));
		RefreshTokenEntity refreshTokenEntity = refreshTokenService.createRefreshToken(userDetails.getId());
		jwtCookieResponse.setJwtRefreshCookies(jwtUtils.generateRefreshJwtCookies(refreshTokenEntity.getToken(), "/api/auth/refresh-token"));
		return jwtCookieResponse;
	}
	
	@Override
	public String getCookiesByRefreshCokies(HttpServletRequest request) {
		String refreshToken = jwtUtils.getRefreshJwtFromCookies(request);
	    if (refreshToken != null && !refreshToken.isEmpty()) {
	        return refreshTokenService.findByToken(refreshToken)
	            .map(refreshTokenService::verifyExpiration) // Hàm này nên ném Exception nếu hết hạn
	            .map(tokenEntity -> {
	                ResponseCookie cookie = jwtUtils.generateJwtCookies(tokenEntity.getUser());
	                return cookie.toString();
	            })
	            .orElse(null);
	    }
	    return null;
	}
	
	@Override
	public JwtCookiesResponse getLogout() {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principle.toString() != "anonymousUser") {
			Long userId = ((MyUserDetails) principle).getId();
			refreshTokenService.deleteByUserId(userId);
		}
		ResponseCookie jwtCookies = jwtUtils.getCleanJwtCookie();
		ResponseCookie jwtRefreshCookies = jwtUtils.getCleanRefreshJwtCookie();
		jwtCookieResponse.setJwtCookies(jwtCookies);
		jwtCookieResponse.setJwtRefreshCookies(jwtRefreshCookies);
		return jwtCookieResponse;
	}
}
