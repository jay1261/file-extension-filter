package com.jay.fileextensionfilter.common.exception;

import com.jay.fileextensionfilter.common.BaseResponse;
import com.jay.fileextensionfilter.common.enums.ErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<Void>> handleCustomException(CustomException ex) {
        BaseResponse<Void> response =
                new BaseResponse<>(ex.getErrorType().getHttpStatus().value(),
                        ex.getErrorType().getMessage(),
                        null);
        return ResponseEntity.status(ex.getErrorType().getHttpStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception ex) {
        ex.printStackTrace();
        BaseResponse<Void> response =
                new BaseResponse<>(ErrorType.INTERNAL_SERVER_ERROR.getHttpStatus().value(),
                        ErrorType.INTERNAL_SERVER_ERROR.getMessage(),
                        null);
        return ResponseEntity.status(ErrorType.INTERNAL_SERVER_ERROR.getHttpStatus()).body(response);
    }
}
