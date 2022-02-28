package com.projects.chat.config;


import hadler.UserHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// https://github.com/liliumbosniacum/websocket/tree/master/src/main/java/com/websocket/wstutorial
// https://www.youtube.com/watch?v=LdQY-OUM2mk&list=PLXy8DQl3058PNFvxOgb5k52Det1DGLWBW&index=3
// https://lahuman.jabsiri.co.kr/202

@Configuration
@EnableWebSocketMessageBroker // websocket 서버를 사용한다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // configureMessageBroker : 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 할 때 사용하는 브로커를 구성한다.
    // Message Broker(메시지 브로커)는 Publisher(송신자)로부터 전달받은 메시지를 Subscriber(수신자)로 전달해주는 중간 역할이며 응용 소프트웨어 간에 메시지를 교환할 수 있게 한다.
    // https://heodolf.tistory.com/49
    // 라우팅 : 어떤 네트워크 안에서 통신 데이터를 보낼 때 최적의 경로를 선택하는 과정이다

    // registry.enableSimpleBroker("/topic");
    // "/topic" 으로 시작하는 주제를 가진 메세지를 핸들러로 라우팅하여 해당 주제에 가입한 모든 클라이언트에게 메세지를 발송한다.
    // registry.setApplicationDestinationPrefixes("/ws");
    // ws 로 시작하는 메세지만 메세지 핸들러로 라우팅한다.
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    // StompEndpointRegistry : 웹소켓에 접속하는 endpoint를 등록한다.
    // 엔드포인트 : 네트워크에 연결되어 있는 최종 장치 ex) 핸드폰, 컴퓨터
    // withSockJs : 브라우져에서 websocket을 지원하지 않을 경우 fallback 옵션을 활성화 하는데 사용됩니다.
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry){
        registry.addEndpoint("/our-websocket")
                .setHandshakeHandler(new UserHandshakeHandler())
                .setAllowedOrigins("http://localhost:3000", "http://yuseon.shop")
//                .setAllowedOrigins("*")
                .withSockJS();
    }

}
