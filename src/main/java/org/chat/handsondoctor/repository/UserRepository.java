package org.chat.handsondoctor.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.chat.handsondoctor.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public UserRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void addUser(User user) {
        dynamoDBMapper.save(user);
    }


    public Optional<User> findById(String nickName) {
        return Optional.ofNullable(dynamoDBMapper.load(User.class, nickName));
    }

    public void deleteUser(String nickName) {
        User user = dynamoDBMapper.load(User.class, nickName);
        if (user != null) {
            dynamoDBMapper.delete(user);
        }
    }
}
