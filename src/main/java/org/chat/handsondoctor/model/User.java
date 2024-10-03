package org.chat.handsondoctor.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

@DynamoDBTable(tableName = "User")
@Builder
@Data
public class User {


    @DynamoDBHashKey(attributeName = "nick_name")
    private String nickName;

    @DynamoDBRangeKey(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "is_online")
    private Boolean isOnline;

    @DynamoDBAttribute(attributeName = "role")
    private String role;
}
