package com.jay.fileextensionfilter.fileextension.controller;

import com.jay.fileextensionfilter.common.BaseResponse;
import com.jay.fileextensionfilter.common.enums.SuccessType;
import com.jay.fileextensionfilter.fileextension.dto.*;
import com.jay.fileextensionfilter.fileextension.service.FileExtensionService;
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
        SuccessType successType = SuccessType.CUSTOM_EXTENSION_CREATED;

                BaseResponse<CustomExtensionDto> response =
                new BaseResponse<>(
                        successType.getHttpStatus().value(),
                        successType.getMessage(),
                        saved
                );

        return ResponseEntity
                .status(successType.getHttpStatus())
                .body(response);
    }

    @PatchMapping("/fixed/{id}")
    public ResponseEntity<BaseResponse<FixedExtensionDto>> updateFixedExtension(
            @PathVariable Long id,
            @Valid @RequestBody FixedExtensionRequestDto request) {

        FixedExtensionDto updated = fileExtensionService.updateBlockedState(id, request);
        SuccessType successType = SuccessType.FIXED_EXTENSION_TOGGLED;

        BaseResponse<FixedExtensionDto> response = new BaseResponse<>(
                successType.getHttpStatus().value(),
                successType.getMessage(),
                updated
        );

        return ResponseEntity
                .status(successType.getHttpStatus())
                .body(response);
    }

    @DeleteMapping("/custom/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCustomExtension(@PathVariable Long id) {
        fileExtensionService.deleteCustomExtension(id);
        SuccessType successType = SuccessType.CUSTOM_EXTENSION_DELETED;

        BaseResponse<Void> response = new BaseResponse<>(
                successType.getHttpStatus().value(),
                successType.getMessage() + " id: " + id,
                null
        );

        return ResponseEntity
                .status(successType.getHttpStatus())
                .body(response);
    }
}
