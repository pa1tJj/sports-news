package com.project.sport.services.auth;

import java.util.Optional;

import com.project.sport.entities.RefreshTokenEntity;

public interface RefreshTokenService {
	public RefreshTokenEntity createRefreshToken(Long id);
	public Optional<RefreshTokenEntity> findByToken(String token);
	public RefreshTokenEntity verifyExpiration(RefreshTokenEntity refreshTokenEntity);
	public int deleteByUserId(Long id);
}
