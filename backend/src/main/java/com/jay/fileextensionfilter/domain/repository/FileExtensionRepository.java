package com.jay.fileextensionfilter.domain.repository;

import com.jay.fileextensionfilter.domain.entity.FileExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileExtensionRepository extends JpaRepository<FileExtension, Long> {

}
