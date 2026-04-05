package com.project.sport.dto.response.dashboard;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardResponse {
	private Long totalPosts;
	private Long totalViews;
	private Long totalUsers;
	private Long newComments;
	private Map<DayOfWeek, Long> viewStats;
	private List<CategoryStatDTO> categoryStats;
	private List<TopPostDTO> topPosts;
}
