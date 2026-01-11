package com.jay.fileextensionfilter.fileextension.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FixedExtensionRequestDto {
    @NotNull(message = "blocked 값은 필수입니다")
    private Boolean blocked;
}
