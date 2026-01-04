package com.jay.fileextensionfilter.domain.controller;

import com.jay.fileextensionfilter.common.BaseResponse;
import com.jay.fileextensionfilter.common.enums.SuccessType;
import com.jay.fileextensionfilter.domain.dto.CustomExtensionDto;
import com.jay.fileextensionfilter.domain.dto.FileExtensionRequestDto;
import com.jay.fileextensionfilter.domain.dto.FileExtensionResponseDto;
import com.jay.fileextensionfilter.domain.service.FileExtensionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file-extensions")
public class FileExtensionController {
    private final FileExtensionService fileExtensionService;

    @GetMapping
    public ResponseEntity<BaseResponse<FileExtensionResponseDto>> getFileExtensions(){
        FileExtensionResponseDto dto = fileExtensionService.getExtensions();
        SuccessType successType = SuccessType.FILE_EXTENSION_LIST_FETCHED;

        BaseResponse<FileExtensionResponseDto> response = new BaseResponse<>(
                successType.getHttpStatus().value(),
                successType.getMessage(),
                dto
        );

        return ResponseEntity.status(successType.getHttpStatus().value()).body(response);
    }

    @PostMapping("/custom")
    public ResponseEntity<BaseResponse<CustomExtensionDto>> createCustomExtension(
            @Valid @RequestBody FileExtensionRequestDto request) {

        CustomExtensionDto saved = fileExtensionService.addCustomExtension(request);

        BaseResponse<CustomExtensionDto> response =
                new BaseResponse<>(
                        SuccessType.CUSTOM_EXTENSION_CREATED.getHttpStatus().value(),
                        SuccessType.CUSTOM_EXTENSION_CREATED.getMessage(),
                        saved
                );

        return ResponseEntity
                .status(SuccessType.CUSTOM_EXTENSION_CREATED.getHttpStatus())
                .body(response);
    }
}
