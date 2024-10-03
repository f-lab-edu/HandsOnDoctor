package org.chat.handsondoctor.interceptor;

import org.chat.handsondoctor.StompPrincipal;
import org.chat.handsondoctor.config.LoggerFactoryConfig;
import org.slf4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@Component
public class WebsocketInterceptor implements HandshakeInterceptor {

    private static final Logger logger= LoggerFactoryConfig.getLogger(WebsocketInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {
        // Autherization token
        logger.debug("메시지 인터셉터 확인");
        boolean isRoomIdHere = getRoomIdFromRequest(request, attributes);
        boolean isVerityPrincipal = getPrincipal(request, attributes);
        if (isRoomIdHere && isVerityPrincipal) return true;
        return false;
    }

    private boolean getPrincipal(ServerHttpRequest request, Map<String, Object> attributes) {
        List<String> authHeaders = request.getHeaders().get("Authorization");

        if (authHeaders != null && !authHeaders.isEmpty()) {
//            String userId = authHeaders.getFirst();
//            attributes.put("principal", new StompPrincipal(userId));
            logger.debug("메시지 인터셉터 : principal");
            return true;
        }
        return true;
    }

    private boolean getRoomIdFromRequest(ServerHttpRequest request, Map<String, Object> attributes) {

        String query = request.getURI().getQuery();
        if (query != null) {
            String[] tokens = query.split("&");
            for (String token : tokens) {
                String[] parts = token.split("=");
                if (parts.length == 2 && parts[0].equals("roomId")) {
                    logger.debug("메시지 인터셉터 : roomId");
                    attributes.put("roomId", new StompPrincipal(parts[1]));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        logger.debug("check the message");
    }

    // AWS Cognito token 검증 및 사용자 ID 반환
}
