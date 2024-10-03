package org.chat.handsondoctor.controller;

import org.chat.handsondoctor.config.LoggerFactoryConfig;
import org.chat.handsondoctor.model.Message;
import org.chat.handsondoctor.service.StompService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class StompController {

    private final StompService stompService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final Logger logger = LoggerFactoryConfig.getLogger(StompController.class);

    public StompController(StompService stompService, SimpMessagingTemplate simpMessagingTemplate) {
        this.stompService = stompService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    // message 전송
    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId,
                            @Payload Message message,
                            Principal principal) {

        String senderId = principal.getName();
        message.setFrom(senderId);
        message.setRoomId(roomId);
        logger.info("before send message : {}", message.getContent());

        simpMessagingTemplate.convertAndSendToUser(
                message.getTo(), "/user/chat/" + roomId, message);
        stompService.sendMessage(message);
    }

    // image 전송
    @MessageMapping("/chat/{roomId}/sendImage")
    public void sendImage(@DestinationVariable String roomId,
                          @Payload Message message,
                          Principal principal) {


        String senderId = principal.getName();
        message.setFrom(senderId);
        message.setRoomId(roomId);
        simpMessagingTemplate.convertAndSend("/user/chat/" + roomId, message);
        stompService.sendImage(message);
    }

    // message 수정
    @MessageMapping("/chat/{roomId}/updateMessage")
    public void updateMessage(@DestinationVariable String roomId,
                              @Payload Message message,
                              Principal principal) {

        String senderId = principal.getName();
        message.setFrom(senderId);
        message.setRoomId(roomId);
    }

    // 2명의 사용자가 모두 채팅방에 접속했을 때 WebSocket 생성 기능
}
