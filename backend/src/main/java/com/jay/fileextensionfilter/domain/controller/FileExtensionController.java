package com.jay.fileextensionfilter.domain.controller;

import com.jay.fileextensionfilter.common.BaseResponse;
import com.jay.fileextensionfilter.common.enums.SuccessType;
import com.jay.fileextensionfilter.domain.dto.FileExtensionRequestDto;
import com.jay.fileextensionfilter.domain.dto.FileExtensionResponseDto;
import com.jay.fileextensionfilter.domain.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
