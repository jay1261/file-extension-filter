package com.jay.fileextensionfilter.domain.entity;

import com.jay.fileextensionfilter.common.enums.ExtensionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class FileExtension extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExtensionType extensionType;

    @Setter
    @Column(nullable = false)
    private boolean blocked;

    public FileExtension(String customName){
        this.name = customName;
        this.blocked = true;
        this.extensionType = ExtensionType.CUSTOM;
    }
}
