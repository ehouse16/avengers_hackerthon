package com.hackerthon5.avengers_BE.movie.dto;

import com.hackerthon5.avengers_BE.review.domain.Review;

import java.util.List;

public record MovieDetailResponse(
        MovieDTO movieDto,
        List<Review> reviews
)
{ }
