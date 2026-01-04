package com.jay.fileextensionfilter.common.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum SuccessType {

    FILE_EXTENSION_LIST_FETCHED(HttpStatus.OK, "File extension list fetched successfully"),

    FIXED_EXTENSION_TOGGLED(HttpStatus.OK, "Fixed extension blocked status toggled successfully"),

    CUSTOM_EXTENSION_CREATED(HttpStatus.CREATED, "Custom extension added successfully"),

    CUSTOM_EXTENSION_DELETED(HttpStatus.OK, "Custom extension deleted successfully");

    private final HttpStatus httpStatus;
    private final String message;
}
