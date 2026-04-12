package com.project.sport.controllers.admin;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.sport.dto.request.news.NewsSaveRequest;
import com.project.sport.dto.request.news.NewsSearchRequest;
import com.project.sport.dto.response.DataResponses;
import com.project.sport.dto.response.news.NewsEditFormResponse;
import com.project.sport.dto.response.news.NewsOptionResponse;
import com.project.sport.services.admin.NewsService;
import com.project.sport.services.storage.FileStorageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class NewsController {
	private final NewsService newsService;
	private final FileStorageService fileStorageService;

	@GetMapping("/news")
	public ResponseEntity<DataResponses> getNews(NewsSearchRequest newsRequest) {
		var newsList = newsService.getNews(newsRequest,
				PageRequest.of(newsRequest.getPage() - 1, newsRequest.getPageSize()));
		var newsFilter = newsService.getNewsFilterData();
		var totalPage = newsService.totalPage();
		return ResponseEntity.ok(DataResponses.of(newsList, newsFilter, totalPage));
	}
	
	@GetMapping(value = "/news/{id}")
	public NewsEditFormResponse getNewsDeatil(@PathVariable Long id) {
		return newsService.getNewsEditForm(id);
	}
	
	@GetMapping(value = "/news/form-data")
	public NewsOptionResponse getNewsOption() {
		return newsService.getNewsOption();
	}
	
	@PostMapping(value = "/news/uploads")
	public ResponseEntity<Map<String, String>> uploads(@RequestPart("file") MultipartFile images) {
		return ResponseEntity.ok(Map.of("url", fileStorageService.saveImage(images)));
	}

	@PostMapping(value = "/news", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createNews(@Valid @RequestPart("newsRequest") NewsSaveRequest newsRequest,
			@RequestPart("thumbnailNews") MultipartFile thumbnail) {
		try {
			newsService.createNews(newsRequest, thumbnail);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping(value = "/news/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<HttpStatus> updateNews(@Valid @RequestPart("newsRequest") NewsSaveRequest newsRequest,
			@RequestPart(name = "thumbnailNews", required = false) MultipartFile images) {
		try {
			newsService.updateNews(newsRequest, images);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/news/{ids}")
	public ResponseEntity<HttpStatus> deleteNews(@PathVariable List<Long> ids) {
		try {
			newsService.deleteNews(ids);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
