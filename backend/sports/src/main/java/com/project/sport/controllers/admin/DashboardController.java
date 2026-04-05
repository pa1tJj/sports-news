package com.project.sport.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sport.dto.response.dashboard.DashboardResponse;
import com.project.sport.services.admin.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class DashboardController {
	private final DashboardService dashboardService;

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponse> getDashboard() {
		try {
			return ResponseEntity.ok(dashboardService.getDashboardStatistics());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
