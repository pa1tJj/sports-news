package com.project.sport.services.admin.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.sport.converters.NewsConverter;
import com.project.sport.dto.request.news.NewsSaveRequest;
import com.project.sport.dto.request.news.NewsSearchRequest;
import com.project.sport.dto.response.news.NewsDetailResponse;
import com.project.sport.dto.response.news.NewsEditFormResponse;
import com.project.sport.dto.response.news.NewsFilterDataResponse;
import com.project.sport.dto.response.news.NewsListResponse;
import com.project.sport.dto.response.news.NewsOptionResponse;
import com.project.sport.entities.NewsEntity;
import com.project.sport.enums.DateOptions;
import com.project.sport.enums.NewsStatus;
import com.project.sport.enums.SortBy;
import com.project.sport.repositories.NewsRepository;
import com.project.sport.services.admin.CategoryService;
import com.project.sport.services.admin.NewsService;
import com.project.sport.services.admin.TagsService;
import com.project.sport.services.admin.UserService;
import com.project.sport.services.storage.FileStorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
	private final UserService userService;
	private final CategoryService categoryService;
	private final NewsRepository newsRepository;
	private final NewsConverter newsConverter;
	private final TagsService tagsService;

	@Override
	public NewsFilterDataResponse getNewsFilterData() {
		NewsFilterDataResponse dataResponse = new NewsFilterDataResponse();
		dataResponse.setSortBy(SortBy.sortBy());
		dataResponse.setCategoryName(categoryService.categoriesName());
		dataResponse.setStatus(NewsStatus.newsStatus());
		dataResponse.setUsers(userService.getUsers());
		dataResponse.setDateOptions(DateOptions.dateOptions());
		return dataResponse;
	}

	@Override
	public List<NewsListResponse> getNews(NewsSearchRequest newsRequest, Pageable pageable) {
		Page<NewsEntity> news = newsRepository.getNews(newsRequest, pageable, totalPage());
		return news.getContent().stream().map(newsConverter::toNewsListResponse).toList();
	}

	@Override
	public Integer totalPage() {
		double total = Math.ceil((double) newsRepository.count() / 5);
		return (int) total;
	}

	@Override
	public void saveNews(NewsSaveRequest newsSaveRequest, MultipartFile images) {
		newsRepository.save(newsConverter.toNewsEntity(newsSaveRequest, images));
	}

	@Override
	public void deleteNews(List<Long> ids) {
		newsRepository.deleteByIdIn(ids);
	}

	@Override
	public NewsDetailResponse newsById(Long id) {
		Optional<NewsEntity> news = newsRepository.findById(id);
		return newsConverter.toNewsDetail(news.get());
	}

	@Override
	public NewsEditFormResponse getNewsEditForm(Long id) {
		NewsEditFormResponse newsEditFormResponse = new NewsEditFormResponse();
		newsEditFormResponse.setNewsDetailResponse(newsById(id));
		newsEditFormResponse.setCategories(categoryService.categoriesName());
		newsEditFormResponse.setStatus(NewsStatus.newsStatus());
		newsEditFormResponse.setTags(tagsService.getTagsList());
		return newsEditFormResponse;
	}

	@Override
	public NewsOptionResponse getNewsOption() {
		NewsOptionResponse newsOptionResponse = new NewsOptionResponse();
		newsOptionResponse.setCategories(categoryService.categoriesName());
		newsOptionResponse.setTags(tagsService.getTagsList());
		newsOptionResponse.setStatus(NewsStatus.newsStatus());
		return newsOptionResponse;
	}

}
