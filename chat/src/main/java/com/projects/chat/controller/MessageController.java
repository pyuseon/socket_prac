package com.projects.chat.controller;


import com.projects.chat.dto.Message;
import com.projects.chat.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {

    // 클라이언트에게 보내는 메세지를 매핑한다.
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
//        Thread.sleep(1000);
        System.out.println("class : " + message.getClass());
        return new ResponseMessage((HtmlUtils.htmlEscape(message.getMessageContent())));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message,
                                             final Principal principal) throws InterruptedException{

        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape("Sending private message to user " + principal.getName() + ": "
        + message.getMessageContent()));
    }
}
