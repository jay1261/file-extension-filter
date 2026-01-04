package com.jay.fileextensionfilter.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    INVALID_EXTENSION_FORMAT(HttpStatus.BAD_REQUEST, "Invalid extension format"),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "Invalid input"),

    FILE_EXTENSION_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "File extension already exists"),

    CANNOT_TOGGLE_CUSTOM_EXTENSION(HttpStatus.BAD_REQUEST, "Cannot toggle a custom extension directly"),
    FILE_EXTENSION_NOT_FOUND(HttpStatus.NOT_FOUND, "File extension not found"),

    MAX_CUSTOM_EXTENSION_REACHED(HttpStatus.BAD_REQUEST, "Maximum number of custom extensions reached (200)"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}