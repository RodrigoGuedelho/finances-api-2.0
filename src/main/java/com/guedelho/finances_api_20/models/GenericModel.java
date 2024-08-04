package com.guedelho.finances_api_20.models;

import com.guedelho.finances_api_20.enums.GenericStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Data
@MappedSuperclass
public abstract class GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private GenericStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
