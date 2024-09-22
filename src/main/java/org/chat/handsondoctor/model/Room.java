package org.chat.handsondoctor.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@DynamoDBTable(tableName = "Room")
@Data
@Builder
public class Room {

    @DynamoDBHashKey(attributeName = "room_id")
    private String roomId;

    @DynamoDBRangeKey(attributeName = "from")
    private String from;

    @DynamoDBAttribute(attributeName = "from_name")
    private String fromName;

    @DynamoDBAttribute(attributeName = "to")
    private String to;

    @DynamoDBAttribute(attributeName = "to_name")
    private String toName;

    @DynamoDBTypeConvertedTimestamp(pattern="yyyyMMddHHmmssSSS")
    private Date lastMessageTime;

    @DynamoDBAttribute(attributeName = "last_message")
    private String lastMessage;

    @DynamoDBAttribute(attributeName = "unread_count")
    private int unreadCount;
}
