package com.jay.fileextensionfilter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FixedExtensionDto {
    private Long id;
    private String name;
    private boolean blocked;
}
