package com.qa.movie_project.exception;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<?> entityNotFoundException(EntityNotFoundException enfe) {
		return new ResponseEntity<>(enfe.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException manve) {
		Map<String, String> fieldErrors = new HashMap<>();

		for (FieldError error : manve.getBindingResult().getFieldErrors()) {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		}

		return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
	}
}
