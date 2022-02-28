package com.example.websocket.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// 채팅 메세지 정의
//@Getter @Setter
//public class ChatMessage {
//    private String chatRoomId;
//    private String writer;
//    private String message;
//    private MessageType type;
//}


@Entity
@Getter @Setter
@NoArgsConstructor
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;
    private String message;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users writer;
}