package org.chat.handsondoctor.websocket;

import org.chat.handsondoctor.handler.CustomStompSessionHandler;
import org.chat.handsondoctor.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketIntegrationTest {

    @LocalServerPort
    private int port;

    private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    @Test
    public void testSendMessage() throws Exception {
        String url = "ws://localhost:" + port + "/ws?roomId=1234";

        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        // StompSessionHandler 구현체 생성
        StompSessionHandler sessionHandler = new CustomStompSessionHandler();

        // 연결 시작
        ListenableFuture<StompSession> future = stompClient.connect(url, headers, sessionHandler);
        StompSession session = future.get(5, TimeUnit.SECONDS);

        // 메시지 전송
        Message message = new Message();
        message.setMessage("Hello World");
        message.setRoomId("1234");
        message.setFrom("user1");
        message.setTo("user2");
        message.setType("TEXT");
        message.setMessageId("dk123ebf1@d");

        session.send("/app/chat/1234/sendMessage", message);
        // 메시지 수신 및 검증 로직 구현 (필요에 따라)
    }
}
