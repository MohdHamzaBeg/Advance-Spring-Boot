package com.udemy.springframework.controllerss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomErrorController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)// Specific method to handle validation exception
	ResponseEntity handleValidationError(MethodArgumentNotValidException exception) {
		
		List errorList = exception.getFieldErrors().stream()
				.map(fieldError->{
					Map<String, String> errorMap = new HashMap<>();
					errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
					return errorMap;
				}).collect(Collectors.toList());
		
		return ResponseEntity.badRequest().body(errorList);
	}
}