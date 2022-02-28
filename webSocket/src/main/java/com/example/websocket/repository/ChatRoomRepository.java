package com.example.websocket.repository;

import com.example.websocket.domain.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;


// 따로 레파지토리를 만들지 않고 리스트로 사용함
@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id){
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name){
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
