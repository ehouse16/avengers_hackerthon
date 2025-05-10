package com.hackerthon5.avengers_BE.review.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackerthon5.avengers_BE.movie.domain.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    private long memberId;
    private String nickname;

    // movieId 필드는 유지하되 DB에 저장되지 않도록 @Transient 추가
    @Transient
    private long movieId;

    // JPA 관계를 위한 Movie 객체 참조 추가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(nullable = false)
    private long memberRate;

    // JSON 직렬화시 movieId 속성으로 출력
    @JsonProperty("movieId")
    public long getMovieIdForJson() {
        return movie != null ? movie.getId() : 0;
    }
}
