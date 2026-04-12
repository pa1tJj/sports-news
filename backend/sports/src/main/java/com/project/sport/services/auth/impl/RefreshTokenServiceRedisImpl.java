package com.project.sport.services.auth.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.project.sport.customexceptions.TokenRefreshException;
import com.project.sport.utils.StringUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceRedisImpl {
	private final RedisTemplate<Object, Object> redisTemplate;
	private static final String REDIS_PREFIX = "refresh-token:";
	
	@Value("${jonet.ent.jwt.refreshExpiration}")
	private Long refreshExpiration;
	
	public void saveRefreshTokenToRedis(String token, String username) {
		String key = REDIS_PREFIX + token;
		redisTemplate.opsForValue().set(key, username, refreshExpiration, TimeUnit.SECONDS);
	}
	
	public String exchangeTokenForUsername(String token) {
		String key = REDIS_PREFIX + token;
		String username = (String) redisTemplate.opsForValue().get(key);
		if (StringUtils.checkString(username)) {
			deleteKeyInRedis(key);
		    return username;
		}
		throw new TokenRefreshException(token, "Token does not exist or has expired.");
	}
	
	public void deleteKeyInRedis(Object key) {
		redisTemplate.delete(key);
	}
}
