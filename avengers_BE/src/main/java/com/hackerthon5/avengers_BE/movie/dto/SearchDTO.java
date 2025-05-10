package com.hackerthon5.avengers_BE.movie.dto;

public record SearchDTO(
        String searchType,
        String keyword
) {
    public SearchDTO {
        // 기본값 설정
        if (searchType == null) searchType = "";
        if (keyword == null) keyword = "";
    }
}
