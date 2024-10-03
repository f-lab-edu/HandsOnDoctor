package org.chat.handsondoctor.handler;

import org.chat.handsondoctor.model.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        // 연결 후 처리 로직
        System.out.println("Connected to the WebSocket server.");
    }

    @Override
    public void handleException(
            StompSession session, StompCommand command, StompHeaders headers,
            byte[] payload, Throwable exception
    ) {
        // 예외 처리 로직
        System.err.println("Got an exception: " + exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        // 전송 에러 처리 로직
        System.err.println("Transport Error: " + exception);
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
        System.out.println("Received : " + msg.getMessage());
    }
}
