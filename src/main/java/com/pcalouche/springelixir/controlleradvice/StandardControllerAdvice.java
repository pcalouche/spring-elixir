package com.pcalouche.springelixir.controlleradvice;

import com.pcalouche.springelixir.exception.ExceptionUtils;
import com.pcalouche.springelixir.exception.JsonExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StandardControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(StandardControllerAdvice.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<JsonExceptionResponse> exceptionResponse(Exception e, HttpServletRequest request) {
		logger.error("Exception caught", e);
		return new ResponseEntity<>(ExceptionUtils.buildJsonErrorResponse(e, request),
				ExceptionUtils.getHttpStatusForException(e));
	}

}