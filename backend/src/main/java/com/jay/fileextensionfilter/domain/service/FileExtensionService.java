package com.jay.fileextensionfilter.domain.service;

import com.jay.fileextensionfilter.common.enums.Type;
import com.jay.fileextensionfilter.domain.dto.CustomExtensionDto;
import com.jay.fileextensionfilter.domain.dto.FileExtensionResponseDto;
import com.jay.fileextensionfilter.domain.dto.FixedExtensionDto;
import com.jay.fileextensionfilter.domain.entity.FileExtension;
import com.jay.fileextensionfilter.domain.repository.FileExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileExtensionService {
    private final FileExtensionRepository fileExtensionRepository;

    public FileExtensionResponseDto getExtensions() {
        List<FileExtension> fixed = fileExtensionRepository.findAllByType(Type.FIXED);
        List<FileExtension> custom = fileExtensionRepository.findAllByType(Type.CUSTOM);

        List<FixedExtensionDto> fixedDtos = fixed.stream()
                .map(e -> new FixedExtensionDto(e.getName(), e.isBlocked()))
                .toList();

        List<CustomExtensionDto> customDtos = custom.stream()
                .map(e -> new CustomExtensionDto(e.getName()))
                .toList();

        return new FileExtensionResponseDto(fixedDtos, customDtos);
    }
}
