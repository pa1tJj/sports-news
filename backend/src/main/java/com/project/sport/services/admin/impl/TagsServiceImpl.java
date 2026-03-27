package com.project.sport.services.admin.impl;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.sport.entities.TagsEntity;
import com.project.sport.repositories.TagsRepository;
import com.project.sport.services.admin.TagsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {
	private final TagsRepository tagsRepository;

	@Override
	public Map<Long, String> getTagsList() {
		return tagsRepository.findAll().
				stream().
				collect(Collectors.toMap(
						TagsEntity::getId,
						TagsEntity::getName
						));
	}

}
