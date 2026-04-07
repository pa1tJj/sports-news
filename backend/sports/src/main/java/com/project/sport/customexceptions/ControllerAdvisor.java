package com.project.sport.customexceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.sport.dto.ErrorMessage;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
			HttpStatusCode httpStatusCode, WebRequest webRequest) {
		Map<String, Object> errors = new HashMap<String, Object>();
		ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setTimestamp(LocalDateTime.now());
		errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
		errorMessage.setErrors(errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
	
	@ExceptionHandler(exception = ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> handleAlreadyExists(ResourceAlreadyExistsException ex, HttpServletRequest request) {
		ErrorMessage errors = new ErrorMessage();
		errors.setMessage(ex.getMessage());
		errors.setStatus(HttpStatus.CONFLICT.value());
		errors.setTimestamp(LocalDateTime.now());
		errors.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handleTokenRefreshException(TokenRefreshException ex) {
		Map<String, Object> errors = new HashMap<String, Object>();
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setTimestamp(LocalDateTime.now());
		errorMessage.setStatus(HttpStatus.FORBIDDEN.value());
		errorMessage.setErrors(errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
}
