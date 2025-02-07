package com.example.contactapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contactapp.model.ChatMessage;

@Repository

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
