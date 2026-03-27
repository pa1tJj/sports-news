package com.project.sport.services.admin;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.project.sport.dto.request.news.NewsSaveRequest;
import com.project.sport.dto.request.news.NewsSearchRequest;
import com.project.sport.dto.response.news.NewsDetailResponse;
import com.project.sport.dto.response.news.NewsEditFormResponse;
import com.project.sport.dto.response.news.NewsFilterDataResponse;
import com.project.sport.dto.response.news.NewsListResponse;
import com.project.sport.dto.response.news.NewsOptionResponse;

public interface NewsService {
	
	public NewsFilterDataResponse getNewsFilterData();
	public List<NewsListResponse> getNews(NewsSearchRequest newsRequest, Pageable pageable);
	public Integer totalPage();
	public void saveNews(NewsSaveRequest newsCreateRequest, MultipartFile images);
	public void deleteNews(List<Long> ids);
	public NewsDetailResponse newsById(Long id);
	public NewsEditFormResponse getNewsEditForm(Long id);
	public NewsOptionResponse getNewsOption();
}
