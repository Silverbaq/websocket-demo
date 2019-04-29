package com.example.websocketdemoserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebsocketDemoserverApplication

fun main(args: Array<String>) {
    runApplication<WebsocketDemoserverApplication>(*args)
}
