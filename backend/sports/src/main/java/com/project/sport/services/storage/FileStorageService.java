package com.project.sport.services.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorageService {
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public String saveImage(MultipartFile multipartFile) {
		try {
			Path path = Paths.get(uploadDir);
			if(!Files.exists(path)) {
				Files.createDirectories(path);
			}
			String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
			Path target = path.resolve(fileName);
			multipartFile.transferTo(target);
			return fileName;
		} catch (IOException e) {
			throw new RuntimeException("Upload fail!", e);
		}
		
	}
}
