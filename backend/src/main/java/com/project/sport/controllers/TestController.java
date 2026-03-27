package com.project.sport.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.sport.services.storage.FileStorageService;

@RestController
public class TestController {
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/api/uploads")
	public void getUploads(@RequestPart MultipartFile images) {
		fileStorageService.saveImage(images);
	}
}
