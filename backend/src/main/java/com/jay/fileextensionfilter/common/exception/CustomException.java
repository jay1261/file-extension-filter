package com.jay.fileextensionfilter.common.exception;


import com.jay.fileextensionfilter.common.enums.ErrorType;

public class CustomException extends RuntimeException {
    private final ErrorType errorType;

    public CustomException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
