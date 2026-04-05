package com.project.sport.services.admin.impl;

import org.springframework.stereotype.Service;

import com.project.sport.dto.response.dashboard.DashboardResponse;
import com.project.sport.enums.NewsStatus;
import com.project.sport.repositories.CategoryRepository;
import com.project.sport.repositories.NewsRepository;
import com.project.sport.repositories.UserRepository;
import com.project.sport.services.admin.DashboardService;
import com.project.sport.services.admin.StatisticsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{
	private final NewsRepository newsRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final StatisticsService statisticsService;
	
	@Override
	public DashboardResponse getDashboardStatistics() {
		DashboardResponse response = new DashboardResponse();
		response.setTotalPosts(newsRepository.count());
		response.setTotalViews(newsRepository.sumTotalViews());
		response.setTotalUsers(userRepository.countByRoles_Code("CUSTOMER"));
		response.setCategoryStats(categoryRepository.getNewsCountByCategory());
		response.setTopPosts(newsRepository.findTop3ByStatusOrderByViewsDesc(NewsStatus.PUBLISHED.name()));
		response.setViewStats(statisticsService.mapViewsToDayOfWeek(newsRepository.viewStatsIn7Days()));
		return response;
	}
	
	
	

}
