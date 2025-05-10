package com.hackerthon5.avengers_BE.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        //예외 로깅(ERROR 레벨)
        log.error("CustomException occurred: ErrorCode = {}, Message = {}", e.getErrorCode(), e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), e.getMessage());

        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception occurred: ErrorCode = {}", e.getMessage());

        ErrorResponse response = ErrorResponse.of("UNKNOWN_ERROR", "An unexpected error occurred");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
