package org.chat.handsondoctor.model;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.Date;

@DynamoDBTable(tableName = "Message")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @DynamoDBHashKey(attributeName = "message_id")
    private String messageId;

    @DynamoDBRangeKey(attributeName = "message_time")
    @DynamoDBTypeConvertedTimestamp(pattern="yyyyMMddHHmmssSSS")
    private String messageTime;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "room_id_index")
    @DynamoDBAttribute(attributeName = "room_id")
    private String roomId;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "from_index")
    @DynamoDBAttribute(attributeName = "from")
    private String from;

    @DynamoDBAttribute(attributeName = "to")
    private String to;

    @DynamoDBAttribute(attributeName = "from_name")
    private String fromName;

    @DynamoDBAttribute(attributeName = "to_name")
    private String toName;

    @DynamoDBAttribute(attributeName = "message")
    private String message;

    @DynamoDBAttribute(attributeName = "type")
    private String type;

    @DynamoDBAttribute(attributeName = "image_url")
    private String imageUrl;
}