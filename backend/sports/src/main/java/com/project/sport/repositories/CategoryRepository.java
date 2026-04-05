package com.project.sport.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.sport.dto.response.dashboard.CategoryStatDTO;
import com.project.sport.entities.CategoryEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	Set<CategoryEntity> findByIdIn(Set<Long> ids);
	void deleteByIdIn(List<Long> ids);
	
	@Modifying
	@Query(value = "DELETE FROM news_category WHERE category_id IN (:ids)", nativeQuery = true)
	void deleteCategoryNews(@Param("ids") List<Long> ids);
	
	@Query(value = """
			       SELECT c.id, c.name, COUNT(n.id) as total FROM categories c INNER JOIN news_category nc ON c.id = nc.category_id
			       INNER JOIN news n ON n.id = nc.news_id GROUP BY c.id, c.name
			       """, nativeQuery = true)
	List<CategoryStatDTO> getNewsCountByCategory();

}
