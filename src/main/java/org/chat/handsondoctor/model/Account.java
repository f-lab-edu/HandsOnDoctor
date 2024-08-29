package org.chat.handsondoctor.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@DynamoDBTable(tableName = "Account")
@Getter
@Setter
@NoArgsConstructor
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
    private Date createdAt;

    @DynamoDBAttribute(attributeName = "role")
    private String role;

    @DynamoDBAttribute(attributeName = "refresh_token")
    private String refreshToken;

    @DynamoDBAttribute(attributeName = "tokneExpiryDate")
    private Date tokenExpiryDate;
}
