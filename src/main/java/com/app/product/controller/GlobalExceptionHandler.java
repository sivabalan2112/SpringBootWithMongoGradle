package com.app.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.product.exception.ApplicationException;

/**
 * 
 * @author Sivabalan
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.UPGRADE_REQUIRED, reason = "Unexpected exception occurred")
	@ExceptionHandler(Exception.class)
	public void handleSQLException(HttpServletRequest request, Exception ex) {
		logger.info(ex.getMessage());
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<String> handleRuntimeException(ApplicationException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}