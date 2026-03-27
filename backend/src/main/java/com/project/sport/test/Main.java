package com.project.sport.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	@Autowired
	private static AnimalService animalService;
	
	public static void main(String[] args) {
		animalService.animalIdentification();
	}
}
