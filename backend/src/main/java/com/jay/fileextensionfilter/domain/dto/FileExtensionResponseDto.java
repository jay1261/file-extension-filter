package com.jay.fileextensionfilter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FileExtensionResponseDto {
    private List<FixedExtensionDto> fixedExtensions;
    private List<CustomExtensionDto> customExtensions;
}
