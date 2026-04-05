package com.project.sport.services.admin.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.project.sport.entities.CategoryEntity;
import com.project.sport.entities.NewsEntity;
import com.project.sport.entities.TagsEntity;
import com.project.sport.enums.DateOptions;
import com.project.sport.enums.NewsStatus;
import com.project.sport.enums.SortBy;
import com.project.sport.repositories.CategoryRepository;
import com.project.sport.repositories.NewsRepository;
import com.project.sport.repositories.TagsRepository;
import com.project.sport.services.admin.CategoryService;
import com.project.sport.services.admin.NewsService;
import com.project.sport.services.admin.TagsService;
import com.project.sport.services.admin.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
	private final UserService userService;
	private final CategoryService categoryService;
	private final NewsRepository newsRepository;
	private final NewsConverter newsConverter;
	private final TagsService tagsService;
	private final CategoryRepository categoryRepository;
	private final TagsRepository tagsRepository;

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

	private Set<CategoryEntity> getCategories(Set<Long> ids) {
		return new HashSet<CategoryEntity>(categoryRepository.findByIdIn(ids));
	}
	
	private Set<TagsEntity> getTags(Set<Long> ids) {
		return new HashSet<TagsEntity>(tagsRepository.findByIdIn(ids));
	}
	
	@Override
	public void createNews(NewsSaveRequest newsSaveRequest, MultipartFile images) {
		Set<CategoryEntity> categoryEntities = getCategories(newsSaveRequest.getCategories());
		Set<TagsEntity> tagsEntities = getTags(newsSaveRequest.getTags());
		NewsEntity newsEntity = newsConverter.toNewsEntityCreate(newsSaveRequest, images, categoryEntities, tagsEntities);
		newsRepository.save(newsEntity);
	}
	
	@Override
	public void updateNews(NewsSaveRequest newsSaveRequest, MultipartFile images) {
		NewsEntity newsOldEntity = newsRepository.findById(newsSaveRequest.getId()).orElseThrow(() -> new RuntimeException("Not found"));
		Set<CategoryEntity> categoryEntities = getCategories(newsSaveRequest.getCategories());
		Set<TagsEntity> tagsEntities = getTags(newsSaveRequest.getTags());
		NewsEntity newsEntity = newsConverter.toNewsEntityUpdate(newsSaveRequest, images, newsOldEntity, categoryEntities, tagsEntities);
		newsRepository.save(newsEntity);
	}

	@Override
	public void deleteNews(List<Long> ids) {
//		newsRepository.findAllById(ids)
//		            .forEach(news -> {
//		            	 news.getCategories(); 
//		            	 news.getTags();
//		            });
//		newsRepository.deleteAllById(ids);
		newsRepository.deleteNewsCategory(ids);
		newsRepository.deleteNewsTag(ids);
		newsRepository.deleteByIdIn(ids); // = DELETE FROM news WHERE id IN (ids)
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
