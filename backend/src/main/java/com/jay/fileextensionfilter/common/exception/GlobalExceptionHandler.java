package com.jay.fileextensionfilter.common.exception;

import com.jay.fileextensionfilter.common.BaseResponse;
import com.jay.fileextensionfilter.common.enums.ErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {

        // 첫 번째 에러 메시지 가져오기
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "Invalid input";

        BaseResponse<Void> response = new BaseResponse<>(
                ErrorType.INVALID_INPUT.getHttpStatus().value(),
                message,
                null
        );

        return ResponseEntity.status(ErrorType.INVALID_INPUT.getHttpStatus()).body(response);
    }

}
