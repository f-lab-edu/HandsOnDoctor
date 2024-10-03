package org.chat.handsondoctor.service;

import org.chat.handsondoctor.model.Message;
import org.chat.handsondoctor.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class StompService {

    private final MessageRepository messageRepository;

    public StompService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void sendMessage(Message message) {
        messageRepository.save(message);
    }

    public void sendImage(Message message) {
        messageRepository.save(message);
    }
}
