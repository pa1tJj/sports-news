package com.project.sport.test;

import org.springframework.stereotype.Component;

//@Component
public class Cat implements Animals{

	@Override
	public void sound() {
		System.out.println("meo meo");
		
	}
	
	
}
