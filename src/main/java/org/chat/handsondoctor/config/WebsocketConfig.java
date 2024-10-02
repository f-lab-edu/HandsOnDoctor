package org.chat.handsondoctor.config;

import org.chat.handsondoctor.interceptor.WebsocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.*;

import java.security.Principal;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer  {

    private WebsocketInterceptor websocketInterceptor;

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


    // 클라이언트 입출력 채널 설정
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        registration.interceptors(new ChannelInterceptor() {

            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    // HandshakeInterceptor에서 저장한 Principal 가져오기
                    // Spring Security 외의 AWS policy에 대해서도 찾아보며, 필요한 요소인지 확인 
                    Principal principal = (Principal) accessor.getSessionAttributes().get("principal");
                    accessor.setUser(principal);
                }

                return message;
            }
        });
    }
}
