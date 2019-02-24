package com.pcalouche.springelixir.controlleradvice;

import com.fasterxml.jackson.databind.JsonNode;
import com.pcalouche.springelixir.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class StandardControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(StandardControllerAdvice.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<JsonNode> exceptionResponse(Exception e, HttpServletRequest request) {
        logger.error("Exception caught", e);
        return new ResponseEntity<>(ExceptionUtils.buildJsonErrorObject(e, request), ExceptionUtils.getHttpStatusForException(e));
    }
}