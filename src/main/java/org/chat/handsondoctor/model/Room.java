package org.chat.handsondoctor.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@DynamoDBTable(tableName = "Room")
@Data
@Builder
public class Room {

    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;

    // user_id를 SK로 설정 시 양측 user_id 저장으로 인해 table에 중복 저장 가능성 o
    // 사용자 화면에서 정렬하기 위해 마지막 메세지 시간을 SK로 설정.
    // Q. 하지만 한 측에서 채팅방 삭제 시, 다른 사용자 측에서도 채팅방이 삭제되어야 하는가?
    @DynamoDBRangeKey(attributeName = "last_message_time")
    @DynamoDBTypeConvertedTimestamp(pattern="yyyyMMddHHmmssSSS")
    private Date lastMessageTime;

    @DynamoDBAttribute(attributeName = "room_id")
    private String roomId;

    @DynamoDBAttribute(attributeName = "nick_name")
    private String nickName;

    @DynamoDBAttribute(attributeName = "other_nick_name")
    private String otherNickName;

    @DynamoDBAttribute(attributeName = "last_message")
    private String lastMessage;

    @DynamoDBAttribute(attributeName = "unread_count")
    private int unreadCount;

    // 2개의 활성화 여부를 판단하여 채팅창 삭제 여부 결정.
    // 한 측에서 채팅방을 삭제하더라도, 상대방 측에서
    @DynamoDBIndexRangeKey(localSecondaryIndexName = "is_active_index")
    @DynamoDBAttribute(attributeName = "is_active")
    private boolean isActive;

    @DynamoDBAttribute(attributeName = "other_user_is_active")
    private boolean otherUserIsActive;
}
