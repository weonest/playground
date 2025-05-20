package com.adventrue

import com.adventrue.command.GoCommand
import com.adventrue.command.QuitCommand
import com.adventrue.common.MessagePrinter
import com.adventrue.room.Castle
import com.adventrue.room.Cave
import com.adventrue.room.Hill
import com.adventrue.room.Pond

class Application

fun main() {
    TextAdventureGame(
        x = 0, y = 0, width = 2, height = 3,
        rooms = listOf(
            Pond(), null, Hill(), Castle(), Hill(), Cave()
        ),
        commands = listOf(
            GoCommand(),
            QuitCommand(),
        ),
        messagePrinter = MessagePrinter(),
    ).run()
}