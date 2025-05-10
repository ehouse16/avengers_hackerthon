package com.hackerthon5.avengers_BE.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;
}
