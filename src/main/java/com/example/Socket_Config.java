package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.websocket.server.ServerEndpoint;


@Configuration
@EnableWebSocket
public class Socket_Config implements WebSocketConfigurer
{
    @Autowired
    WebSocketHandler socketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler,"/chat");
        System.out.println("소켓 핸들러");
    }

}
