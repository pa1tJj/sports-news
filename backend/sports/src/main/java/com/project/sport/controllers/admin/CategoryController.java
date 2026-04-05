package com.project.sport.controllers.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sport.dto.request.category.CategoryRequest;
import com.project.sport.dto.response.category.CategoryResponse;
import com.project.sport.services.admin.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryResponse>> getCategories() {
		try {
			return ResponseEntity.ok(categoryService.getListCategory());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponse> getCategoryDetail(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(categoryService.getCategoryDetail(id));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping("/categories")
	public ResponseEntity<HttpStatus> create(@RequestBody CategoryRequest categoryRequest) {
		try {
			categoryService.saveCategory(categoryRequest);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<HttpStatus> update(@RequestBody CategoryRequest categoryRequest) {
		try {
			categoryService.saveCategory(categoryRequest);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/categories/{ids}")
	public ResponseEntity<HttpStatus> delete(@PathVariable List<Long> ids) {
		try {
			categoryService.deleteCategory(ids);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
