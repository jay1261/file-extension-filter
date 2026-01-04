package com.jay.fileextensionfilter.domain.service;

import com.jay.fileextensionfilter.common.enums.ErrorType;
import com.jay.fileextensionfilter.common.enums.Type;
import com.jay.fileextensionfilter.common.exception.CustomException;
import com.jay.fileextensionfilter.domain.dto.*;
import com.jay.fileextensionfilter.domain.entity.FileExtension;
import com.jay.fileextensionfilter.domain.repository.FileExtensionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileExtensionService {
    private final FileExtensionRepository fileExtensionRepository;

    public FileExtensionResponseDto getExtensions() {
        List<FileExtension> fixed = fileExtensionRepository.findAllByType(Type.FIXED);
        List<FileExtension> custom = fileExtensionRepository.findAllByType(Type.CUSTOM);

        List<FixedExtensionDto> fixedDtos = fixed.stream()
                .map(e -> new FixedExtensionDto(e.getId(), e.getName(), e.isBlocked()))
                .toList();

        List<CustomExtensionDto> customDtos = custom.stream()
                .map(e -> new CustomExtensionDto(e.getId(), e.getName()))
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
        long customCount = fileExtensionRepository.countByType(Type.CUSTOM);
        if (customCount >= 200) {
            throw new CustomException(ErrorType.MAX_CUSTOM_EXTENSION_REACHED);
        }

        FileExtension fileExtension = new FileExtension(normalized);

        FileExtension saved = fileExtensionRepository.save(fileExtension);

        return new CustomExtensionDto(saved.getId(), saved.getName());
    }

    @Transactional
    public FixedExtensionDto updateBlockedState(Long id, FixedExtensionRequestDto request) {
        FileExtension fileExtension = fileExtensionRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorType.FILE_EXTENSION_NOT_FOUND)
        );

        if(fileExtension.getType().equals(Type.CUSTOM)){
            throw new CustomException(ErrorType.CANNOT_TOGGLE_CUSTOM_EXTENSION);
        }

        fileExtension.setBlocked(request.getBlocked());

        return new FixedExtensionDto(fileExtension.getId(), fileExtension.getName(), fileExtension.isBlocked());
    }

    @Transactional
    public void deleteCustomExtension(Long id) {
        FileExtension fileExtension = fileExtensionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.FILE_EXTENSION_NOT_FOUND));

        if(fileExtension.getType() != Type.CUSTOM) {
            throw new CustomException(ErrorType.CANNOT_DELETE_FIXED_EXTENSION);
        }

        fileExtensionRepository.delete(fileExtension);
    }
}
