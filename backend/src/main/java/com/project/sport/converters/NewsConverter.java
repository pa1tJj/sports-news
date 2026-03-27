package com.project.sport.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.project.sport.dto.request.news.NewsSaveRequest;
import com.project.sport.dto.response.news.NewsDetailResponse;
import com.project.sport.dto.response.news.NewsListResponse;
import com.project.sport.entities.CategoryEntity;
import com.project.sport.entities.NewsEntity;
import com.project.sport.entities.TagsEntity;
import com.project.sport.services.storage.FileStorageService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewsConverter {
	private final ModelMapper mapper;
	private final FileStorageService fileStorageService;

	public LocalDateTime stringToDate(String data) {
		if(data != null) {
		    LocalDateTime dateTime = LocalDateTime.now();
			if(data.equals("YESTERDAY")) {
				dateTime = dateTime.minusDays(1);
			} else if(data.equals("LAST_7_DAYS")) {
				dateTime = dateTime.minusDays(7);
			} else if(data.equals("LAST_30_DAYS")) {
				dateTime = dateTime.minusDays(30);
			}
		    return dateTime;
		}
		return null;
	}
	
	public NewsListResponse toNewsListResponse(NewsEntity newsEntity) {
		NewsListResponse newsResponse = mapper.map(newsEntity, NewsListResponse.class);
		newsResponse.setCategoryName(categoriesName(newsEntity).stream().collect(Collectors.joining(",")));
		String createdDate = newsEntity.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a"));
		newsResponse.setCreatedDate(createdDate);
		return newsResponse;
	}
	
	public Set<String> categoriesName(NewsEntity newsEntity) {
		return newsEntity.getCategories().stream().map(CategoryEntity::getName).collect(Collectors.toSet());
	}
	
	public NewsEntity toNewsEntity(NewsSaveRequest newsSaveRequest, MultipartFile images) {
		NewsEntity newsEntity = mapper.map(newsSaveRequest, NewsEntity.class);
		if(images != null) {
			newsEntity.setThumbnail(fileStorageService.saveImage(images));
		}
		return newsEntity;
	}
	
	public NewsDetailResponse toNewsDetail(NewsEntity newsEntity) {
		NewsDetailResponse newsDetailResponse = mapper.map(newsEntity, NewsDetailResponse.class);
		newsDetailResponse.setCategories(
				newsEntity.getCategories()
				.stream()
				.collect(Collectors.toMap(
						CategoryEntity::getId,
						CategoryEntity::getName
						)));
		newsDetailResponse.setTags(
				newsEntity.getTags()
				.stream()
				.collect(Collectors.toMap(
						TagsEntity::getId,
						TagsEntity::getName)));
		newsDetailResponse.setThumbnailPreview(newsEntity.getThumbnail());
		return newsDetailResponse;
	}
	

}
