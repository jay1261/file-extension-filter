package com.jay.fileextensionfilter.domain.service;

import com.jay.fileextensionfilter.domain.repository.FileExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileExtensionService {
    private final FileExtensionRepository fileExtensionRepository;

}
