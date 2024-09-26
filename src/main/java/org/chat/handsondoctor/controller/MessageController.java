package org.chat.handsondoctor.controller;

import org.chat.handsondoctor.service.MessageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


}
