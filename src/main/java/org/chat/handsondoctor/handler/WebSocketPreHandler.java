package org.chat.handsondoctor.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebSocketPreHandler implements ChannelInterceptor {

    /**
     * 메시지가 채널로 전송되기 전에 실행
     *
     * @param message 메시지 객체
     * @param channel 메시지 채널
     * @return 수정된 메시지 객체
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        System.out.println("신호가 여기 오는지 확인.");

        StompCommand command = accessor.getCommand();
        // TODO : 안전하게 Null 처리를 할 다른 방법이 있는지 확인.
        if (command != null && StompCommand.CONNECT.equals(command)) {
            // 토큰 검증 로직
            String token = accessor.getFirstNativeHeader("Authorization");
//            Authentication auth = authenticateToken(token);
//            accessor.setUser(auth);
        }

        System.out.println("메시지 확인 전 반환.");
        
        return message;
    }

    private Authentication authenticateToken(String token) {
        // 토큰을 검증하고 사용자 정보를 반환하는 로직
        // 예시로 사용자 ID를 추출했다고 가정
//        String userId = tokenService.getUserIdFromToken(token);
//        return new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
        return null;
    }
}

// TODO : 각 역할이 온전하게 되고 있는지 간단한 Websocket 연결 테스트 : 테스트 코드를 통해 확인하기 o
// TODO : 각 코드가 Sonar에 걸리지 않게끔 각 역할을 제대로 부여하여, 결과적으로 Websocket과 STOMP 프로토콜이 정상 작동하는지 확인.
// TODO : 각 메시지를 전송할 때마다 로그를 어떻게 남길 것인지 잠시 고민하기(이건 후순위라서 그렇게까지 고민안해도 됨)
// TODO : 사용자의 정보를 어떻제 저장할 것인지 체크해놓기.
// TODO : OAuth가 무엇인지 찾아놓고, 서버 단에서만 해결이 가능한 선이라면 적용해보기
// TODO : ECS에 해당 서비스를 올리는 작업을 진행하기
// TODO : ECS에서 해당 서비스가 정상적으로 작동하는지 적용해보기
// TODO : Lambda와 S3, CloudWatch 등 다양한 AWS 연결 및 IAM 권한 확인
// TODO :