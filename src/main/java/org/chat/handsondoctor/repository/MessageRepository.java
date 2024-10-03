package org.chat.handsondoctor.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.chat.handsondoctor.model.Message;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public MessageRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void save(Message message) {
        dynamoDBMapper.save(message);
    }
}
