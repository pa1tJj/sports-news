package com.project.sport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import com.project.sport.filters.CsrfCookieFilter;
import com.project.sport.filters.JwtFilters;
import com.project.sport.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final UserDetailsService userDetailsService;
	private final JwtUtils jwtUtils;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.cors(Customizer.withDefaults())
		    .csrf(csrf -> csrf 
		    		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // frontend có thể đọc để gửi lại trong header
		    		.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()) //hỗ trợ xử lý token từ header/param
		    		.ignoringRequestMatchers("/api/auth/**")
		    	 )
		    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authorizeHttpRequests(auth -> { auth
			    .requestMatchers("/api/admin/**").hasAnyRole("ADMIN", "STAFF", "MANAGER")
			    .requestMatchers("/api/web/**").permitAll()
			    .requestMatchers("/api/auth/**").permitAll()
			    .anyRequest().authenticated();
		    })
		    .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
		    .addFilterBefore(jwtFilters(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public JwtFilters jwtFilters() {
		return new JwtFilters(jwtUtils, userDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
}
