package com.example.contactapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime timestamp;

    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = ZonedDateTime.now();
    }
}
