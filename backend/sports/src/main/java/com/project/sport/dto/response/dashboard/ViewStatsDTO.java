package com.project.sport.dto.response.dashboard;

import java.time.LocalDate;


public interface ViewStatsDTO {
	LocalDate getDate();
	Long getTotalView();
}
