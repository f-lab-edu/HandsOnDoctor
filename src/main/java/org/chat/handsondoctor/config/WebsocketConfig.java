package org.chat.handsondoctor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");  // 메시지를 구독하는 경로
        config.setApplicationDestinationPrefixes("/app");  // 클라이언트에서 메시지를 보낼 때 사용되는 경로
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")  // WebSocket 연결 엔드포인트
                .setAllowedOriginPatterns("*")
                .withSockJS();  // SockJS를 통한 fallback 지원
    }
}
