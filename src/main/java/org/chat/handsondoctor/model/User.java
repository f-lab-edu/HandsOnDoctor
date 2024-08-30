package org.chat.handsondoctor.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

@DynamoDBTable(tableName = "User")
@Data
public class User {

    // PK : nickName;
    @DynamoDBHashKey(attributeName = "nick_name")
    private String nickName;

    // SK : userId;
    @DynamoDBRangeKey(attributeName = "user_id")
    private String userId;

    // LSI : isOnline;
    @DynamoDBAttribute(attributeName = "is_online")
    @DynamoDBIndexRangeKey(localSecondaryIndexName = "is_online_index")
    private String isOnline;

    @DynamoDBAttribute(attributeName = "role")
    private String role;
}
