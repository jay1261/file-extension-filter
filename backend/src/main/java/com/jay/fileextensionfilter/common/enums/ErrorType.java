package com.jay.fileextensionfilter.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    INVALID_EXTENSION_FORMAT(HttpStatus.BAD_REQUEST, "확장자 형식이 올바르지 않습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),
    FILE_EXTENSION_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 등록된 확장자입니다."),
    CANNOT_TOGGLE_CUSTOM_EXTENSION(HttpStatus.BAD_REQUEST, "커스텀 확장자는 직접 토글할 수 없습니다."),
    CANNOT_DELETE_FIXED_EXTENSION(HttpStatus.BAD_REQUEST, "고정 확장자는 삭제할 수 없습니다."),
    FILE_EXTENSION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 확장자를 찾을 수 없습니다."),
    MAX_CUSTOM_EXTENSION_REACHED(HttpStatus.BAD_REQUEST, "커스텀 확장자는 최대 200개까지 등록할 수 있습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}