package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class Socket_Config implements WebSocketConfigurer {
    @Autowired
    Socket_Handler socketHandler;

    @Autowired
    dbqudfufhandler dbqudfufhandler1;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/chat").addInterceptors(new SocketHandlerInterceptor());
        registry.addHandler(dbqudfufhandler1, "/gamescreen");
        System.out.println("소켓 핸들러");
    }
}
