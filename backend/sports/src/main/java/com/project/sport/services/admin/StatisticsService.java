package com.project.sport.services.admin;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.sport.dto.response.dashboard.ViewStatsDTO;

@Service
public class StatisticsService {
	
	public Map<DayOfWeek, Long> mapViewsToDayOfWeek(List<ViewStatsDTO> viewStats) {
		Map<DayOfWeek, Long> result = new LinkedHashMap<>();
		for(DayOfWeek day : DayOfWeek.values()) {
			result.put(day, 0L);
		}
		for(ViewStatsDTO it : viewStats) {
			result.put(it.getDate().getDayOfWeek(), it.getTotalView());
		}
		return result;
	}
}
