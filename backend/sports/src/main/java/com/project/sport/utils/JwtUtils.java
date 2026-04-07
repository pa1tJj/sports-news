package com.project.sport.utils;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.project.sport.entities.UserEntity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${jonet.ent.jwt.secret}")
	private String jwtSecret;
	
	@Value("${jonet.ent.jwt.expiration}")
	private Long expiration;
	
	@Value("${jonet.ent.jwt.cookiesName}")
	private String cookiesName;
	
	@Value("${jonet.ent.jwt.refreshCookiesName}")
	private String refreshCookiesName;
	
	@Value("${jonet.ent.jwt.refreshExpiration}")
	private Long refreshExpiration;
	
	private Key getSingingKeys() {
		byte[] bytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(bytes);
	}
	
	public String generateToken(String username) {
		String token = Jwts.builder()
				.setSubject(username)
				.signWith(getSingingKeys(), SignatureAlgorithm.HS256)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.compact();
		return token;
	}
	
	public ResponseCookie generateJwtCookies(UserDetails userDetails) {
		String token = generateToken(userDetails.getUsername());
		return generateCookies(cookiesName, token, "/", 3600);
	}
	
	public ResponseCookie generateCookies(String name, String value, String path, long maxAge) {
		ResponseCookie cookie = ResponseCookie.from(name, value)
				                              .httpOnly(true)// Ngăn chặn XSS, JavaScript không thể đọc được token
				                              .secure(false) // chỉ gửi qua HTTPS
				                              .sameSite("Lax") //chống CSRF
				                              .path(path) //có hiệu lực cho toàn bộ domain
				                              .maxAge(maxAge) //thời gian sống của cookie
				                              .build();
		return cookie;
	}

	public ResponseCookie generateJwtCookies(UserEntity userEntity) {
		String token = generateToken(userEntity.getUsername());
		return generateCookies(cookiesName, token, "/", 3600);
	}
	
	public ResponseCookie generateRefreshJwtCookies(String refreshToken, String path) {
		return generateCookies(refreshCookiesName, refreshToken, path, 86400);
	}
	
	public ResponseCookie getCleanJwtCookie() {
		ResponseCookie cookie = ResponseCookie.from(cookiesName, null).maxAge(0).path("/").build();
		return cookie;
	}

	public ResponseCookie getCleanRefreshJwtCookie() {
		ResponseCookie cookie = ResponseCookie.from(refreshCookiesName, null).maxAge(0).path("/").build();
		return cookie;
	}
	
	public String getCookieValueByName(HttpServletRequest request, String name) {
		Cookie cookie = WebUtils.getCookie(request, name);
		if(cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}
	
	public String getJwtFromCookies(HttpServletRequest request) {
		return getCookieValueByName(request, cookiesName);
	}
	
	public String getRefreshJwtFromCookies(HttpServletRequest request) {
		return getCookieValueByName(request, refreshCookiesName);
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSingingKeys())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	
	public Boolean validateJwtToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSingingKeys()).build().parse(token);
			return true;
		} catch (MalformedJwtException e) { //ngoại lệ trả về khi token sai định dạng, cấu trúc chuẩn như không đúng format header.payload.signature
			                                //token bị sửa/cắt - token không phải jwt - decode sai cách
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) { // khi token hết hạn
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {//xảy ra khi token đúng format nhưng không dùng được: parse sai cách - thuật toán k hỗ trợ
			                                  //Không phải chuẩn Base64 URL
			                                  //Token từ hệ thống khác (Google, Auth0, Firebase…) nhưng parse như token nội bộ
			                                  //header k hợp lệ
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}
