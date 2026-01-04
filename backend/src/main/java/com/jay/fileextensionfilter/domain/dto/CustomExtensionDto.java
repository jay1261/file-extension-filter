package com.jay.fileextensionfilter.domain.dto;

import com.jay.fileextensionfilter.common.enums.ExtensionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomExtensionDto {
    private Long id;
    private String name;
    private ExtensionType extensionType;
}
