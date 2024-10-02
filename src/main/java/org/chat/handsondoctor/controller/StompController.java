package org.chat.handsondoctor.controller;

import org.chat.handsondoctor.model.Message;
import org.chat.handsondoctor.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class StompController {

    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public StompController(MessageService messageService, SimpMessagingTemplate simpMessagingTemplate) {
        this.messageService = messageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    // message 전송
    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(Message message) {
        simpMessagingTemplate.convertAndSendToUser(
                message.getTo(), "/chat/" + message.getRoomId(), message);
        messageService.sendMessage(message);
    }

    // image 전송
    @MessageMapping("/chat/{roomId}/sendImage")
    public void sendImage(Message message) {
        simpMessagingTemplate.convertAndSend("/chat/" + message.getRoomId(), message);
        messageService.sendImage(message);
    }


}
