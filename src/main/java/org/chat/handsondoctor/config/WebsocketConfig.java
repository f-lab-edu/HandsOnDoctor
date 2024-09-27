package org.chat.handsondoctor.config;

import org.chat.handsondoctor.handler.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    // session 관리 확인 필요
    private final WebSocketHandler webSocketHandler;

    public WebsocketConfig(WebSocketHandler webSockectHandler) {
        this.webSocketHandler = webSockectHandler;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/topic/{roomId}");
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        // 추후 대규모 서비스에서 정상 작동하는지 추측 후 수정 필요.
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(500000);
        container.setMaxBinaryMessageBufferSize(500000);
        return container;
    }
}
