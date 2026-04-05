package com.project.sport.services.admin;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.project.sport.dto.request.tag.TagRequest;
import com.project.sport.dto.response.tag.TagResponse;

public interface TagsService {
	public Map<Long, String> getTagsList();
	List<TagResponse> getTags(String name, Pageable pageable);
	TagResponse getTagDetail(Long id);
	void saveTag(TagRequest tagRequest);
	void deleteTags(List<Long> ids);
	Integer totalPage();
}
