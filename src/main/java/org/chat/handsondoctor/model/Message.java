package org.chat.handsondoctor.model;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

@DynamoDBTable(tableName = "Message")
@Data
@Builder
public class Message {

    @DynamoDBHashKey(attributeName = "room_id")
    private String roomId;

    // timestamp + message_id
    @DynamoDBRangeKey(attributeName = "message_sort_key")
    private String messageSortKey;

    @DynamoDBAttribute(attributeName = "nick_name")
    private String nickName;

    @DynamoDBAttribute(attributeName = "receiver_name")
    private String receiverName;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "user_id_index")
    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "receiver_id")
    private String receiverId;

    @DynamoDBAttribute(attributeName = "message")
    private String message;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "type_index")
    @DynamoDBAttribute(attributeName = "type_index")
    private String type;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "is_read_index")
    @DynamoDBAttribute(attributeName = "is_read")
    private boolean isRead;

    @DynamoDBAttribute(attributeName = "image_url")
    private String imageUrl;
}