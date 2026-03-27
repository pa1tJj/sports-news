package com.project.sport.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

	/* tight coupling : phụ thuộc quá chặt vào class, khó mở rộng, bảo trì
	Dog dog = new Dog();
	Cat cat = new Cat();
	Bird bird = new Bird();
	
	public void animalIdentification() {
		dog.sound();
		cat.sound();
		bird.sound();
	}
	*/
	
	/* loose couplong*/
	
	//sử dụng @Autowired
//	@Autowired
	private Animals animals;
	
	public AnimalService() { }
	
	/* cách thức truyền/inject theo loose coupling 
	// inject qua constructor
	public AnimalService(Animals animals) {
		this.animals = animals;
	}
	
	// inject qua setter
	public void setAnimals(Animals animals) {
		this.animals = animals;
	}
	*/
	
	public void animalIdentification() {
		animals.sound();
	}
}
