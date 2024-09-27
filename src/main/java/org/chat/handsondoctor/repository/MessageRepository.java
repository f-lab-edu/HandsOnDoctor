package org.chat.handsondoctor.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.chat.handsondoctor.model.Message;
import org.chat.handsondoctor.model.Room;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class MessageRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public MessageRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void save(Message message) {
        dynamoDBMapper.save(message);
    }

    public List<Message> findMessagesByRoomId(String roomId) {
        return Collections.singletonList(dynamoDBMapper.load(Message.class, roomId));
    }

    public List<Message> findMessagesByMessageId(String messageId) {
        return Collections.singletonList(dynamoDBMapper.load(Message.class, messageId));
    }

    public List<Message> findMessagesByUserId(String userId) {
        return Collections.singletonList(dynamoDBMapper.load(Message.class, userId));
    }

//    public Room createRoomByUserId(String userId) {
//        // 최근 roomId 조회해서 없으면 새로 만들고, 있으면 해당 값 기준으로 새롭게 만들기?
//        // 이 부분은 고민해서 처리하기.
//    }
}
