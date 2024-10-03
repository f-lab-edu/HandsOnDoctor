package org.chat.handsondoctor.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.Date;

@DynamoDBTable(tableName = "Account")
@Data
public class Account {

    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;

    @DynamoDBRangeKey(attributeName = "nick_name")
    private String nickName;

    @DynamoDBAttribute(attributeName = "email")
    private String email;

    @DynamoDBAttribute(attributeName = "password")
    private String password;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "phone_number")
    private String phoneNumber;

    @DynamoDBAttribute(attributeName = "create_at")
    @DynamoDBTypeConvertedTimestamp(pattern="yyyyMMddHHmmssSSS")
    private Date createdAt;

    @DynamoDBAttribute(attributeName = "role")
    private String role;

    @DynamoDBAttribute(attributeName = "refresh_token")
    private String refreshToken;

    @DynamoDBAttribute(attributeName = "tokenExpiryDate")
    @DynamoDBTypeConvertedTimestamp(pattern="yyyyMMddHHmmssSSS")
    private Date tokenExpiryDate;
}
