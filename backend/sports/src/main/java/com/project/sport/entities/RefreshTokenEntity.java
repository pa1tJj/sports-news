package com.project.sport.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "refreshtoken")
@Getter
@Setter
public class RefreshTokenEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "token", nullable = true, unique = true)
	private String token;
	
	@Column(name = "expiry_date", nullable = true)
	private Instant expiryDate;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity user;
}
