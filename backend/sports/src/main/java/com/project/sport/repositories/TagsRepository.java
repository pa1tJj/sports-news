package com.project.sport.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.sport.entities.TagsEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface TagsRepository extends JpaRepository<TagsEntity, Long>{
	Set<TagsEntity> findByIdIn(Set<Long> ids);
	void deleteByIdIn(List<Long> ids);
	Page<TagsEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
	
	@Modifying
	@Query(value = "DELETE FROM news_tag WHERE tag_id IN (:ids)", nativeQuery = true)
	void deleteTagNews(@Param("ids") List<Long> ids);
}
