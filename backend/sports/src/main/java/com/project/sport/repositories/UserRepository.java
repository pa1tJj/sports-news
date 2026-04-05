package com.project.sport.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.sport.entities.UserEntity;
import com.project.sport.repositories.custom.UserRepositoryCustom;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom{
	UserEntity findByUsername(String username);

	@Query(value = "SELECT u.* FROM users u INNER JOIN user_role ur ON u.id = user_id INNER JOIN roles r ON r.id = role_id WHERE r.code IN (:roles)"
			, nativeQuery = true)
	Page<UserEntity> findUsersExcludeRoles(@Param("roles") List<String> roles, Pageable pageable);
	
	@Modifying
	@Query(value = "DELETE FROM user_role WHERE user_id IN (:ids)", nativeQuery = true)
	void deleteUserRole(@Param("ids") List<Long> ids);
	
	void deleteByIdIn(List<Long> ids);
	
	Boolean existsByUsernameIgnoreCase(String username);
	
	Long countByRoles_Code(String code);
}
