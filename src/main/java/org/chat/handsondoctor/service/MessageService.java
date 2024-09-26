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

    public void sendImage(Message message) {
        // S3에 연결 및 저장.
        // 해당 URL를 DynamoDB에 저장.
        // 메세지를 사용자에게 전달. (이미지 형태로)
    }

    public void sendMessage(Message message) {
        //
    }
}
