package com.project.sport.customexceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
