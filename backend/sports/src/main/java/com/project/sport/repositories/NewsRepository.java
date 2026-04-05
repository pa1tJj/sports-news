package com.project.sport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.sport.dto.response.dashboard.TopPostDTO;
import com.project.sport.dto.response.dashboard.ViewStatsDTO;
import com.project.sport.entities.NewsEntity;
import com.project.sport.repositories.custom.NewsRepositoryCustom;

import jakarta.transaction.Transactional;

@Transactional
public interface NewsRepository extends JpaRepository<NewsEntity, Long>, NewsRepositoryCustom{
	public void deleteByIdIn(List<Long> ids);
	
	@Modifying
	@Query(value = "DELETE FROM news_category WHERE news_id IN (:ids)", nativeQuery = true)
	void deleteNewsCategory(@Param("ids") List<Long> ids);
	
	@Modifying
	@Query(value = "DELETE FROM news_tag WHERE news_id IN (:ids)", nativeQuery = true)
	void deleteNewsTag(@Param("ids") List<Long> ids);
	
	@Query(value = "SELECT SUM(views) FROM news", nativeQuery = true)
	Long sumTotalViews();
	
	List<TopPostDTO> findTop3ByStatusOrderByViewsDesc(String status);
	
	@Query(value = """
			SELECT SUM(n.views) AS totalView, DATE(n.createddate) AS date FROM news n 
			WHERE n.createddate >= NOW() - INTERVAL 7 DAY 
			GROUP BY DATE(n.createddate)	
			""", nativeQuery = true)
	List<ViewStatsDTO> viewStatsIn7Days();
}
