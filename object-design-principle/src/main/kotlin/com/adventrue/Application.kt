package com.adventrue

import com.adventrue.command.GoCommand
import com.adventrue.common.MessagePrinter
import com.adventrue.room.Room

class Application

fun main() {
    TextAdventureGame(
        rooms = listOf(
            Room("성", "성에 도착했습니다."),
            Room("숲", "숲에 도착했습니다."),
            Room("강", "강에 도착했습니다."),
            Room("산", "산에 도착했습니다."),
        ),
        commands = listOf(
            GoCommand(),
            QuitCommand(),
        ),
        messagePrinter = MessagePrinter(),
    )
}