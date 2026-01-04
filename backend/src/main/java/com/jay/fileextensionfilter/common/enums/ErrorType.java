package com.jay.fileextensionfilter.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    INVALID_EXTENSION_FORMAT(HttpStatus.BAD_REQUEST, "Invalid extension format"),
    EXTENSION_TOO_LONG(HttpStatus.BAD_REQUEST, "Extension exceeds maximum length (20 characters)"),
    EXTENSION_EMPTY(HttpStatus.BAD_REQUEST, "Extension cannot be empty"),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "Invalid input"),


    FILE_EXTENSION_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "File extension already exists"),

    CANNOT_MODIFY_FIXED_EXTENSION(HttpStatus.BAD_REQUEST, "Cannot modify a fixed extension directly"),
    CUSTOM_EXTENSION_NOT_FOUND(HttpStatus.NOT_FOUND, "Custom extension not found"),

    INVALID_CHARACTERS(HttpStatus.BAD_REQUEST, "Extension contains invalid characters"),
    MAX_CUSTOM_EXTENSION_REACHED(HttpStatus.BAD_REQUEST, "Maximum number of custom extensions reached (200)"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}