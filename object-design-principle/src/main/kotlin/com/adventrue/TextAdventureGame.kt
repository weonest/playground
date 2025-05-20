package com.adventrue

import com.adventrue.command.Command
import com.adventrue.common.MessagePrinter
import com.adventrue.room.Room

class TextAdventureGame(
    private var x: Int = 0,
    private var y: Int = 0,
    private val width: Int = 0,
    private val height: Int = 0,
    private val rooms: List<Room?>,
    private val commands: List<Command>,
    private val messagePrinter: MessagePrinter,
) {
    private var isRunning: Boolean = true

    fun run() {
        val currentRoomIndex = x + y * width
        var currentRoom = rooms[currentRoomIndex]

        // 시작 위치에는 항상 방이 존재
        messagePrinter.printWelcomeMessage(currentRoom!!.name, currentRoom.description)
        while (isRunning) {
            try {
                val input = readlnOrNull()
                    ?.trim()
                    ?.lowercase()
                    ?: continue
                val inputArr = input.split(" ")
                val command = inputArr[0]
                val argument = inputArr.getOrNull(1)

                val commandObj = commands.find { it.name == command }
                    ?: throw IllegalArgumentException("이해할 수 없는 명령어입니다.")

                val result = commandObj.execute(argument)

                if (result == -1) {
                    isRunning = false
                }

                currentRoom = rooms[result]

                if (currentRoom == null) {
                    println("이동할 수 없습니다.")
                    continue
                }

                x = result % width
                y = result / width
                messagePrinter.printRoomMessage(currentRoom)
            }
            catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
