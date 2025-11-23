package com.futurowork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "messages_queue_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageQueueLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String routingKey;
    @Column(columnDefinition = "TEXT")
    private String payload;
}
