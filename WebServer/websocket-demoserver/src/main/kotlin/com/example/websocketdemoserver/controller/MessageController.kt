package com.example.websocketdemoserver.controller

import com.example.websocketdemoserver.model.Greeting
import com.example.websocketdemoserver.model.HelloMessage
import org.springframework.stereotype.Controller
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.util.HtmlUtils


@Controller
class MessageController{

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @Throws(Exception::class)
    fun greeting(message: HelloMessage): Greeting {
        Thread.sleep(1000) // simulated delay
        return Greeting("Hello, " + HtmlUtils.htmlEscape(message.name) + "!")
    }
}