package com.project.sport.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sport.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	Set<RoleEntity> findByCode(String code);
}
