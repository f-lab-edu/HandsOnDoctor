package org.chat.handsondoctor.service;

import org.chat.handsondoctor.model.Message;
import org.chat.handsondoctor.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    /**
     * 동작방식은 다를 수 있으나 StompService와 동일한 기능을 제공해주어야 한다.
    * */

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // S3에 연결 및 저장.
    // 해당 URL를 DynamoDB에 저장.
    // 메세지를 사용자에게 전달. (이미지 형태로)
}
