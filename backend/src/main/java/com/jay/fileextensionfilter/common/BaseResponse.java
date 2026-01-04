package com.jay.fileextensionfilter.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private Integer httpCode;
    private String message;
    private T data;
}
