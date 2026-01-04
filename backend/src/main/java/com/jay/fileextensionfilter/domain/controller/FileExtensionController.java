package com.jay.fileextensionfilter.domain.controller;

import com.jay.fileextensionfilter.domain.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileExtensionController {
    private final FileExtensionService fileExtensionService;

}
