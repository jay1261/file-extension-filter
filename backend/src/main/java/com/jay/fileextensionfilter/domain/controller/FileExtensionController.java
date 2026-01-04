package com.jay.fileextensionfilter.domain.controller;

import com.jay.fileextensionfilter.domain.dto.FileExtensionResponseDto;
import com.jay.fileextensionfilter.domain.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file-extensions")
public class FileExtensionController {
    private final FileExtensionService fileExtensionService;

    @GetMapping
    public FileExtensionResponseDto getFileExtensions(){
        return fileExtensionService.getExtensions();
    }
}
