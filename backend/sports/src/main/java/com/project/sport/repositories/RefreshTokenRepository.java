package com.project.sport.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sport.entities.RefreshTokenEntity;
import com.project.sport.entities.UserEntity;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
	int deleteByUser(UserEntity userEntity);
}
