package com.udemy.springframework.controllerss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

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
	
	@ExceptionHandler
	ResponseEntity handleJPAViolation(TransactionSystemException exception){ //Another type of exception thrown during the use of jpa
		ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();
		if(exception.getCause().getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException ve = (ConstraintViolationException) exception.getCause().getCause();
			List errors = ve.getConstraintViolations().stream()
					.map(constraintViolation->{
						Map<String, String> errMap = new HashMap<>();
						errMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
						return errMap;
					}).collect(Collectors.toList());
		return responseEntity.body(errors);
		}
		return responseEntity.build();
	}
}
