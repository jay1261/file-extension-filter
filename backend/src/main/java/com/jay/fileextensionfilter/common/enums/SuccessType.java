package com.jay.fileextensionfilter.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessType {
    FILE_EXTENSION_LIST_FETCHED(HttpStatus.OK, "파일 확장자 목록 조회에 성공했습니다."),
    FIXED_EXTENSION_TOGGLED(HttpStatus.OK, "고정 확장자 차단 상태가 변경되었습니다."),
    CUSTOM_EXTENSION_CREATED(HttpStatus.CREATED, "커스텀 확장자가 추가되었습니다."),
    CUSTOM_EXTENSION_DELETED(HttpStatus.OK, "커스텀 확장자가 삭제되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}