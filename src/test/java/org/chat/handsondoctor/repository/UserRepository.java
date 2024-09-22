package org.chat.handsondoctor.repository;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.chat.handsondoctor.model.User;

public class UserRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public UserRepository(AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public void saveUser(User user) {
        dynamoDBMapper.save(user);
    }
}
