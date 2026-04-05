package com.project.sport.services.admin.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.sport.converters.TagConverter;
import com.project.sport.dto.request.tag.TagRequest;
import com.project.sport.dto.response.tag.TagResponse;
import com.project.sport.entities.TagsEntity;
import com.project.sport.repositories.TagsRepository;
import com.project.sport.services.admin.TagsService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TagsServiceImpl implements TagsService {
	private final TagsRepository tagsRepository;
	private final TagConverter tagConverter;

	@Override
	public Map<Long, String> getTagsList() {
		return tagsRepository.findAll().stream().collect(Collectors.toMap(TagsEntity::getId, TagsEntity::getName));
	}

	@Override
	public List<TagResponse> getTags(String name, Pageable pageable) {
		if (name != null && !name.isEmpty()) {
			return tagsRepository.findByNameContainingIgnoreCase(name, pageable).stream()
					.map(tagConverter::toTagResponse).collect(Collectors.toList());
		}
		return tagsRepository.findAll(pageable).stream().map(tagConverter::toTagResponse).collect(Collectors.toList());
	}

	@Override
	public TagResponse getTagDetail(Long id) {
		return tagConverter.toTagResponse(tagsRepository.findById(id).orElseThrow());
	}

	@Override
	public void saveTag(TagRequest tagRequest) {
		tagsRepository.save(tagConverter.toTagsEntity(tagRequest));
	}

	@Override
	public void deleteTags(List<Long> ids) {
		tagsRepository.deleteTagNews(ids);
		tagsRepository.deleteByIdIn(ids);
	}

	@Override
	public Integer totalPage() {
		Double total = (double) tagsRepository.count() / 5;
		return (int) Math.ceil(total);
	}

}
