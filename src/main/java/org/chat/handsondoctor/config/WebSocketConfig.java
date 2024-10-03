package org.chat.handsondoctor.config;

import org.chat.handsondoctor.handler.StompErrorHandler;
import org.chat.handsondoctor.handler.WebSocketPreHandler;
import org.chat.handsondoctor.interceptor.WebsocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer  {

    private final WebsocketInterceptor websocketInterceptor;
    private final WebSocketPreHandler webSocketPreHandler;
    private final StompErrorHandler stompErrorHandler;

    public WebSocketConfig(WebsocketInterceptor websocketInterceptor,
                           WebSocketPreHandler webSocketPreHandler,
                           StompErrorHandler stompErrorHandler) {

        this.websocketInterceptor = websocketInterceptor;
        this.webSocketPreHandler = webSocketPreHandler;
        this.stompErrorHandler = stompErrorHandler;
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지를 전달하는 목적지 경로를 설정
        // TODO : enableSimpleBroker를 통해 어떤 Websocket을 열고자 하는지 찾아봐야 한다.
        config.enableSimpleBroker("/topic", "/queue", "/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 WebSocket에 연결할 엔드포인트 설정
        registry.addEndpoint("/ws")
                .addInterceptors(websocketInterceptor)
                .setAllowedOriginPatterns("*");
        registry.setErrorHandler(stompErrorHandler);
    }


    /**
    *  메시지 요청/응답에 관한 인터셉터
    * */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketPreHandler);
    }
}
