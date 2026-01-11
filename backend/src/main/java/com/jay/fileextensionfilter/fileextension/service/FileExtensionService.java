package com.jay.fileextensionfilter.fileextension.service;

import com.jay.fileextensionfilter.common.enums.ErrorType;
import com.jay.fileextensionfilter.common.enums.ExtensionType;
import com.jay.fileextensionfilter.common.exception.CustomException;
import com.jay.fileextensionfilter.fileextension.dto.*;
import com.jay.fileextensionfilter.fileextension.entity.FileExtension;
import com.jay.fileextensionfilter.fileextension.repository.FileExtensionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileExtensionService {
    private final FileExtensionRepository fileExtensionRepository;

    public FileExtensionResponseDto getExtensions() {
        List<FileExtension> fixed = fileExtensionRepository.findAllByExtensionType(ExtensionType.FIXED);
        List<FileExtension> custom = fileExtensionRepository.findAllByExtensionType(ExtensionType.CUSTOM);

        List<FixedExtensionDto> fixedDtos = fixed.stream()
                .map(e -> new FixedExtensionDto(e.getId(), e.getName(), e.isBlocked(), e.getExtensionType()))
                .toList();

        List<CustomExtensionDto> customDtos = custom.stream()
                .map(e -> new CustomExtensionDto(e.getId(), e.getName(), e.getExtensionType()))
                .toList();

        return new FileExtensionResponseDto(fixedDtos, customDtos);
    }

    @Transactional
    public CustomExtensionDto addCustomExtension(FileExtensionRequestDto request) {
        // 공백,점 제거 및 소문자 변환 후 검증
        String normalized = request.getName()
                .trim()
                .replace(".", "")
                .toLowerCase();
        if(!normalized.matches("^[a-z0-9]+$")) {
            throw new CustomException(ErrorType.INVALID_EXTENSION_FORMAT);
        }

        // 중복 체크
        if(fileExtensionRepository.existsByName(normalized)) {
            throw new CustomException(ErrorType.FILE_EXTENSION_ALREADY_EXIST);
        }

        // 커스텀 200개 제한
        long customCount = fileExtensionRepository.countByExtensionType(ExtensionType.CUSTOM);
        if (customCount >= 200) {
            throw new CustomException(ErrorType.MAX_CUSTOM_EXTENSION_REACHED);
        }

        FileExtension fileExtension = new FileExtension(normalized);

        FileExtension saved = fileExtensionRepository.save(fileExtension);

        return new CustomExtensionDto(saved.getId(), saved.getName(), saved.getExtensionType());
    }

    @Transactional
    public FixedExtensionDto updateBlockedState(Long id, FixedExtensionRequestDto request) {
        FileExtension fileExtension = fileExtensionRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorType.FILE_EXTENSION_NOT_FOUND)
        );

        if(fileExtension.getExtensionType().equals(ExtensionType.CUSTOM)){
            throw new CustomException(ErrorType.CANNOT_TOGGLE_CUSTOM_EXTENSION);
        }

        fileExtension.setBlocked(request.getBlocked());

        return new FixedExtensionDto(fileExtension.getId(), fileExtension.getName(), fileExtension.isBlocked(), fileExtension.getExtensionType());
    }

    @Transactional
    public void deleteCustomExtension(Long id) {
        FileExtension fileExtension = fileExtensionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.FILE_EXTENSION_NOT_FOUND));

        if(fileExtension.getExtensionType() != ExtensionType.CUSTOM) {
            throw new CustomException(ErrorType.CANNOT_DELETE_FIXED_EXTENSION);
        }

        fileExtensionRepository.delete(fileExtension);
    }
}
