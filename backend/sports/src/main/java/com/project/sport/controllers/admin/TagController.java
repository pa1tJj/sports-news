package com.project.sport.controllers.admin;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.sport.dto.request.tag.TagRequest;
import com.project.sport.dto.response.DataResponses;
import com.project.sport.dto.response.tag.TagResponse;
import com.project.sport.services.admin.TagsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class TagController {
	private final TagsService tagsService;
	private final DataResponses dataResponses;
	
	@GetMapping("/tags")
	public ResponseEntity<DataResponses> getTags(@RequestParam(name = "name") String name, @RequestParam(name = "page", defaultValue = "1") Integer page) {
		try {
			dataResponses.setList(tagsService.getTags(name, PageRequest.of(page - 1, 5)));
			dataResponses.setTotalPage(tagsService.totalPage());
			return ResponseEntity.ok(dataResponses);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/tags/{id}")
	public ResponseEntity<TagResponse> getTagDetail(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(tagsService.getTagDetail(id));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping("/tags")
	public ResponseEntity<HttpStatus> createTag(@RequestBody TagRequest tagRequest) {
		try {
			tagsService.saveTag(tagRequest);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/tags/{id}")
	public ResponseEntity<HttpStatus> updateTag(@RequestBody TagRequest tagRequest) {
		try {
			tagsService.saveTag(tagRequest);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/tags/{ids}")
	public ResponseEntity<HttpStatus> deleteTag(@PathVariable List<Long> ids) {
		try {
			tagsService.deleteTags(ids);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
