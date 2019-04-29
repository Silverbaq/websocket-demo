package com.example.websocketdemoserver.controller

import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import com.example.websocketdemoserver.model.ChatMessage
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller


@Controller
class ChatController {

    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/public")
    fun sendMessage(@Payload chatMessage: ChatMessage): ChatMessage {
        return chatMessage
    }

    @MessageMapping("/chat/addUser")
    @SendTo("/topic/public")
    fun addUser(@Payload chatMessage: ChatMessage,
                headerAccessor: SimpMessageHeaderAccessor): ChatMessage {
        // Add username in web socket session
        headerAccessor.sessionAttributes!!["username"] = chatMessage.sender
        return chatMessage
    }

}