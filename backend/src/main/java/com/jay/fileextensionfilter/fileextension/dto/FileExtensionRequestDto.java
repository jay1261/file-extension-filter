package com.jay.fileextensionfilter.fileextension.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class FileExtensionRequestDto {
    @NotBlank(message = "확장자를 입력해주세요")
    @Size(max = 20, message = "확장자는 최대 20자까지 가능합니다")
    private String name;
}
