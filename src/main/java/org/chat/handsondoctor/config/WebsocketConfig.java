package org.chat.handsondoctor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer  {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지를 전달하는 목적지 경로를 설정
        config.enableSimpleBroker("/topic", "/queue", "/user");  // 클라이언트가 구독할 수 있는 경로들
        config.setApplicationDestinationPrefixes("/app");  // 클라이언트가 메시지를 보낼 경로의 Prefix
        config.setUserDestinationPrefix("/user"); // 특정 사용자에게 메시지를 보낼 경로의 Prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 WebSocket에 연결할 엔드포인트 설정
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();  // SockJS 사용 (WebSocket을 지원하지 않는 브라우저에 대한 대체 방식)
    }
}
