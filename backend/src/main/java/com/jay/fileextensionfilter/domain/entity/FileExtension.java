package com.jay.fileextensionfilter.domain.entity;

import com.jay.fileextensionfilter.common.enums.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Type type;

    @Column(nullable = false)
    private boolean blocked;

    public FileExtension(String customName){
        this.name = customName;
        this.blocked = true;
        this.type = Type.CUSTOM;
    }
}
