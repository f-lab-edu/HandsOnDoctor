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
    public void sendMessage(@Payload Message message, Principal principal) {

        String senderId = principal.getName();
        message.setFrom(senderId);

        simpMessagingTemplate.convertAndSendToUser(
                message.getTo(), "/chat/" + message.getRoomId(), message);
        messageService.sendMessage(message);
    }

    // image 전송
    @MessageMapping("/chat/{roomId}/sendImage")
    public void sendImage(@Payload Message message, Principal principal) {

        String senderId = principal.getName();
        message.setFrom(senderId);
        simpMessagingTemplate.convertAndSend("/chat/" + message.getRoomId(), message);
        messageService.sendImage(message);
    }


}
