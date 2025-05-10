package com.hackerthon5.avengers_BE.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiExceptionCode {

    INVALID_INPUT("4001", "Invalid input provided.", HttpStatus.BAD_REQUEST),
    INVALID_SEARCH_TPYE("4001", "지원하지 않는 검색 타입입니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND("4041", "Resource not found.", HttpStatus.NOT_FOUND),
    GENRE_NOT_FOUND("4042", "장르를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    MOVIE_NOT_FOUND("4043", "영화를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SERVER_ERROR("5001", "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;         // 에러 코드
    private final String message;      // 에러 메시지
    private final HttpStatus httpStatus; // HTTP 상태 코드
}
