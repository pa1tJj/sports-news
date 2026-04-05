package com.project.sport.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.sport.dto.request.tag.TagRequest;
import com.project.sport.dto.response.tag.TagResponse;
import com.project.sport.entities.TagsEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagConverter {
	private final ModelMapper mapper;
	
	public TagResponse toTagResponse(TagsEntity tagsEntity) {
		return mapper.map(tagsEntity, TagResponse.class);
	}
	
	public TagsEntity toTagsEntity(TagRequest tagRequest) {
		return mapper.map(tagRequest, TagsEntity.class);
	}
}
