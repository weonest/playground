package com.adventrue

import com.adventrue.command.Command
import com.adventrue.common.MessagePrinter
import com.adventrue.room.Room

class TextAdventureGame(
    private val rooms: List<Room>,
    private val commands: List<Command>,
    private val messagePrinter: MessagePrinter,
) {
    private val currentRoomIndex = 0
    private val isRunning: Boolean = true

    fun run() {
        messagePrinter.printWelcomeMessage(rooms[currentRoomIndex].name)
        while (isRunning) {
            try {
                val input = readlnOrNull()
                    ?.trim()
                    ?.lowercase()
                    ?: continue
                val inputParts = input.split(" ")
                val command = inputParts[0]
                val argument = inputParts.getOrNull(1)

                commands.stream()
                    .filter { it.name == command }
                    .findFirst()
                    .ifPresentOrElse(
                        { it.execute(argument) },
                        { println("지원 하는 명령어가 아닙니다.") }
                    )
            }
            catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
