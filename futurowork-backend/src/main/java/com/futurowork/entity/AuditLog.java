package com.futurowork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "audit_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private String entity;
    private String entityId;
    @Column(columnDefinition = "TEXT")
    private String payload;
}
