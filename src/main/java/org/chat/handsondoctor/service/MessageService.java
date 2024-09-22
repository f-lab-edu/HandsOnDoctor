package org.chat.handsondoctor.service;

import org.chat.handsondoctor.model.Message;
import org.chat.handsondoctor.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessageByRoomId(String roomId) {
        return messageRepository.findMessagesByRoomId(roomId);
    }

    public List<Message> getMessageByDoctorId(String doctorId) {
        return messageRepository.findMessagesByUserId(doctorId);
    }
}
