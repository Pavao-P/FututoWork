package com.futurowork.repository;

import com.futurowork.entity.MessageQueueLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageQueueLogRepository extends JpaRepository<MessageQueueLog, Long> {
}
