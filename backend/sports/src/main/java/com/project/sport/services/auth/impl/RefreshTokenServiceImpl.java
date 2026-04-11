package com.project.sport.services.auth.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.sport.entities.RefreshTokenEntity;
import com.project.sport.repositories.RefreshTokenRepository;
import com.project.sport.repositories.UserRepository;
import com.project.sport.services.auth.RefreshTokenService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService{
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;
	
	@Value("${jonet.ent.jwt.refreshExpiration}")
	private Long refreshExpiration;

	@Override
	public RefreshTokenEntity createRefreshToken(Long id) {
		RefreshTokenEntity entity = new RefreshTokenEntity();
		entity.setUser(userRepository.findById(id).orElseThrow());
		entity.setToken(UUID.randomUUID().toString());
		entity.setExpiryDate(Instant.now().plusMillis(refreshExpiration * 1000));
		refreshTokenRepository.save(entity);
		return entity;
	}

	@Override
	public Optional<RefreshTokenEntity> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	@Override
	public RefreshTokenEntity verifyExpiration(RefreshTokenEntity tokenEntity) {
		if (tokenEntity.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(tokenEntity);
		}
		return tokenEntity;
	}

	@Override
	public int deleteByUserId(Long id) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(id).get());
	}

}
