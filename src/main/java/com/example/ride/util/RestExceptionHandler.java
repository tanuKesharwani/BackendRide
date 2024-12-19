package com.example.ride.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value = GenericException.class)
	public ResponseEntity<ErrorResponse> handleGenericException(GenericException ex) {
		ErrorResponse error = new ErrorResponse(500, ex.getMessage(), new Date());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = DuplicateRecordException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateRecordException(DuplicateRecordException ex) {
		ErrorResponse error = new ErrorResponse(409, ex.getMessage(), new Date());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = NoRecordFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoRecordFoundException(NoRecordFoundException ex) {
		ErrorResponse error = new ErrorResponse(400, ex.getMessage(), new Date());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(404, ex.getMessage(), new Date());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	
}
