package com.example.websocketdemoserver.model

class ChatMessage(
        var type: MessageType,
        val content: String,
        var sender: String
) {

    enum class MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}