package org.chat.handsondoctor.websocket;

import org.chat.handsondoctor.model.Message;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;


public class StompClientTest {

    public static void main(String[] args) throws Exception {
        String url = "ws://localhost:8080/ws?roomId=1234";

        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        StompSessionHandler sessionHandler = new CustomStompSessionHandler();
        StompSession session = stompClient.connect(url, sessionHandler).get();
        Message message = new Message();
        message.setMessage("Hello World");
        message.setRoomId("1234");
        message.setFrom("user1");
        message.setTo("user2");
        message.setType("TEXT");
        message.setMessageId("dk123ebf1@d");

        // 메시지 전송
        session.send("/app/chat/1234/sendMessage", message);
    }

    private static class CustomStompSessionHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            // 구독 설정 등
            session.subscribe("/topic/chat/1234", this);
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            System.out.println("Received: " + payload);
        }
    }
}