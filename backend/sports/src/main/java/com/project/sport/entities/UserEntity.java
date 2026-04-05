package com.project.sport.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity{
	private static final long serialVersionUID = -23794917531919926L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique = false)
	private String username;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "status")
	private String status;
	
	@ManyToMany
	@JoinTable(name = "user_role",
	           joinColumns =  @JoinColumn(name = "user_id", nullable = false),
	           inverseJoinColumns =  @JoinColumn(name =  "role_id", nullable = false))
	private Set<RoleEntity> roles;
	
	@OneToMany(mappedBy = "user")
	private List<NewsEntity> news = new ArrayList<>();
}
