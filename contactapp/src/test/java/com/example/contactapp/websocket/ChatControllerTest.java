package com.example.contactapp.websocket;

import com.example.contactapp.ContactappApplicationTests;
import com.example.contactapp.controller.ChatController;
import com.example.contactapp.model.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ContactappApplicationTests

class ChatControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ChatController chatController;

    private final BlockingQueue<ChatMessage> messages = new LinkedBlockingDeque<>();

    @Test
    void testChatMessageSendAndReceive() throws Exception {

        WebSocketClient webSocketClient = new SockJsClient(
                Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient())));

        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        stompClient.setMessageConverter(converter);

        StompSession stompSession = stompClient.connectAsync(
                "ws://localhost:" + port + "/chat-websocket",
                new WebSocketHttpHeaders(),
                new StompSessionHandlerAdapter() {
                }).get(10, TimeUnit.SECONDS);

        stompSession.subscribe("/topic/messages", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return ChatMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                messages.offer((ChatMessage) payload);
            }
        });

        ChatMessage chatMessage = new ChatMessage("John", "Hello!");
        stompSession.send("/app/sendMessage", chatMessage);

        ChatMessage receivedMessage = messages.poll(2, TimeUnit.SECONDS);

        assertThat(receivedMessage).isNotNull();
        assertThat(receivedMessage.getSender()).isEqualTo("John");
        assertThat(receivedMessage.getContent()).isEqualTo("Hello!");
    }
}
