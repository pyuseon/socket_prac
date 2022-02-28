package com.projects.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {
    private String content;

    public ResponseMessage(String content) {
        this.content = content;
    }
}
