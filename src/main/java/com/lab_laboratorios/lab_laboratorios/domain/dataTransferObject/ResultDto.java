package com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
@Getter
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto<T> {
    private final String message;
    private final boolean success;
    private final T data;

     public static <T> ResultDto<T> ok(T data) {
        return ResultDto.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <T> ResultDto<T> fail(String message) {
        return ResultDto.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
    
    public static <T> ResultDto<T> fail(List<String> messages) {
        return ResultDto.<T>builder()
                .success(false)
                .message(String.join("; ", messages))
                .build();
    }
    public String getErrorMessage() {
        return message;
    }
}
