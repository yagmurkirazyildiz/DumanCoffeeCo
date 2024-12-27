package com.yagmur.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorMessage {

    private int code;
    private String message;
    private List<String> fields;
    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

}