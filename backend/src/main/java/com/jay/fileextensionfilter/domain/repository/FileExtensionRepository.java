package com.jay.fileextensionfilter.domain.repository;

import com.jay.fileextensionfilter.common.enums.Type;
import com.jay.fileextensionfilter.domain.entity.FileExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileExtensionRepository extends JpaRepository<FileExtension, Long> {

    List<FileExtension> findAllByType(Type type);
}
