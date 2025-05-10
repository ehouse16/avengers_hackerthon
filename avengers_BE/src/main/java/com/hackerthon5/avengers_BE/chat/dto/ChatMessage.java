package com.hackerthon5.avengers_BE.chat.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String movieId;
    private String sender;
    private String message;
}
