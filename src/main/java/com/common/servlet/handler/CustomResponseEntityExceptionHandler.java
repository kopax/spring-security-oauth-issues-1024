package com.common.servlet.handler;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);

	private Gson gson = new Gson();

	/**
	 * Error 401 : Authentication refused
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler({ AuthenticationCredentialsNotFoundException.class })
	public ResponseEntity<Object> handleAuthenticationCredentialsNotFoundException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(gson.toJson(ex.getMessage()), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Error 403 : Access denied
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(gson.toJson(ex.getMessage()), new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

}
