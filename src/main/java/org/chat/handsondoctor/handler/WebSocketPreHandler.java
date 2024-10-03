package org.chat.handsondoctor.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.chat.handsondoctor.config.LoggerFactoryConfig;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebSocketPreHandler implements ChannelInterceptor {

    private static final Logger logger =
            LoggerFactoryConfig.getLogger(WebSocketPreHandler.class);

    /**
     * 메시지가 채널로 전송되기 전에 실행
     *
     * @param message 메시지 객체
     * @param channel 메시지 채널
     * @return 수정된 메시지 객체
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        logger.debug("preSend");
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        StompCommand command = accessor.getCommand();

        if (command != null && StompCommand.CONNECT.equals(command)) {
            // 토큰 검증 로직
            logger.debug("token authorization : {}", accessor.getSessionId());
        }
        return message;
    }

    // 토큰을 검증하고 사용자 정보를 반환하는 로직
    // 예시로 사용자 ID를 추출했다고 가정
}
