package com.hackerthon5.avengers_BE.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final HttpStatus status; // Http 상태값
    private final String errorCode; //에러 코드
    private final String errorMessage; //에러 메시지

    public CustomException(ApiExceptionCode errorCode){
        this.status = errorCode.getHttpStatus();
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }
}
