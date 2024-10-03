package org.chat.handsondoctor.handler;

import org.chat.handsondoctor.config.LoggerFactoryConfig;
import org.chat.handsondoctor.model.Message;
import org.slf4j.Logger;
import org.springframework.lang.NonNullApi;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    private static final Logger logger= LoggerFactoryConfig.getLogger(CustomStompSessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        // 연결 후 처리 로직
        logger.debug("Connected to the WebSocket server.");
    }

    @Override
    public void handleException(
            StompSession session, StompCommand command, StompHeaders headers,
            byte[] payload, Throwable exception
    ) {
        // 예외 처리 로직
        logger.debug("Got an exception: {}", exception.getMessage());
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        // 전송 에러 처리 로직
        logger.debug("Transport Error: {}", exception.getMessage());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        // 수신한 메시지의 타입 정의
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        // 수신한 메시지 처리 로직
        Message msg = (Message) payload;
        logger.debug("Received : {}", msg.getContent());
    }
}
