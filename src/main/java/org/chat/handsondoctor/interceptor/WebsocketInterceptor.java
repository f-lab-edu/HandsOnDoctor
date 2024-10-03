package org.chat.handsondoctor.interceptor;

import org.chat.handsondoctor.StompPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Component
public class WebsocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // Autherization token
        System.out.println("메시지 인터셉터 확인");
//        boolean isPrincipalEffective = getPrincipal(request, attributes);
        boolean isRoomIdHere = getRoomIdFromRequest(request, attributes);
        // TODO : 추후 Cognito 추가 후 token 확인 절차 및 사용자 정보 확인 절차 추가.
        // TODO : 메시지 내용이 적합한지 확인하는 절차도 함께 확인해야 한다.
//        boolean isVerifyCognitoToken = verifyCognitoToken(token);

        if (isRoomIdHere) {
            return true;
        }
        System.out.println("메시지 인터셉터 : false");

        return false;
    }

    private boolean getPrincipal(ServerHttpRequest request, Map<String, Object> attributes) {
        List<String> authHeaders = request.getHeaders().get("Authorization");

        if (authHeaders != null && !authHeaders.isEmpty()) {
            String userId = authHeaders.get(0);
            attributes.put("principal", new StompPrincipal(userId));
            System.out.println("메시지 인터셉터 : principal");
            return true;
        }
        return false;
    }

    private boolean getRoomIdFromRequest(ServerHttpRequest request, Map<String, Object> attributes) {

        String query = request.getURI().getQuery();
        if (query != null) {
            String[] tokens = query.split("&");
            for (String token : tokens) {
                String[] parts = token.split("=");
                if (parts.length == 2 && parts[0].equals("roomId")) {
                    System.out.println("메시지 인터셉터 : roomId");
                    attributes.put("roomId", new StompPrincipal(parts[1]));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }

    private String verifyCognitoToken(String token) {
        // AWS Cognito token 검증 및 사용자 ID 반환
        return null;
    }



}
