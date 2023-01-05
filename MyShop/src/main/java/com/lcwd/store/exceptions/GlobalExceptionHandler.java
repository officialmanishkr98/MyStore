package com.lcwd.store.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.lcwd.store.dtos.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	// resource not found exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleRunTimeException(RuntimeException e){
		logger.info("RuntimeException generated : {}", e.getMessage() );
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message(e.getMessage()).success(false).build(),HttpStatus.NOT_FOUND);
		
	}
	
	//handling for invalid exception
	@ExceptionHandler(InvalidAgeException.class)
	public ResponseEntity<ApiResponse> handleRuntimeException(InvalidAgeException e){
		logger.info("Invalid age generated : {} ", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message(e.getMessage()).success(false).build(),HttpStatus.BAD_REQUEST);
	}
	
	//method for handling validation exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handelValidationException(MethodArgumentNotValidException e){
		Map<String, String> responseMap = new HashMap<>();
		
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		allErrors.forEach(error ->{
			String defaultMessage = error.getDefaultMessage();
			String fieldError = ((FieldError) error).getField();
			responseMap.put(fieldError, defaultMessage);
		});
			
		return new ResponseEntity<Map<String,String>>(responseMap,HttpStatus.BAD_GATEWAY);
		
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ApiResponse> handleEmptyResultFromDatabase(EmptyResultDataAccessException e){
		logger.info("Result is empty : {} ", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message("Result is empty: Data does not exists").success(false).build(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse> handleTypeMissmatchException(MethodArgumentTypeMismatchException e){
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message("type did not match, please enter int for id").success(false).build(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException e){
		logger.info("Result is empty : {} ", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message(e.getMessage()).success(false).build(),HttpStatus.BAD_REQUEST);
	}
	
	
}
