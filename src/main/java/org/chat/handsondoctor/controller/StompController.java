package org.chat.handsondoctor.controller;

import org.chat.handsondoctor.model.Message;
import org.chat.handsondoctor.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.security.Principal;

public class StompController {

    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public StompController(MessageService messageService, SimpMessagingTemplate simpMessagingTemplate) {
        this.messageService = messageService;
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
        System.out.println("sending message TEST");

        simpMessagingTemplate.convertAndSendToUser(
                message.getTo(), "/user/chat/" + roomId, message);
        messageService.sendMessage(message);
    }

    // image 전송
    @MessageMapping("/chat/{roomId}/sendImage")
    public void sendImage(@DestinationVariable String roomId,
                          @Payload Message message,
                          Principal principal) {

        System.out.println("Received message in room " + roomId + ": " + message.getMessage());

        String senderId = principal.getName();
        message.setFrom(senderId);
        message.setRoomId(roomId);
        simpMessagingTemplate.convertAndSend("/user/chat/" + roomId, message);
        messageService.sendImage(message);
    }

    // message 수정
    @MessageMapping("/chat/{roomId}/updateMessage")
    public void updateMessage(@DestinationVariable String roomId,
                              @Payload Message message,
                              Principal principal) {

        String senderId = principal.getName();
        message.setFrom(senderId);
        message.setRoomId(roomId);
        System.out.println("update message TEST");

        // TODO : simpMessingTemplate에서 메시지 수정과 관련된 메시지가 있는지 확인.
//        simpMessagingTemplate.("/user/chat/" + roomId, message);

    }


    // 2명의 사용자가 모두 채팅방에 접속했을 때 WebSocket 생성 기능
}
