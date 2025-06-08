package com.adventrue.game

import com.adventrue.command.Command
import com.adventrue.command.CommandContext
import com.adventrue.command.CommandParser
import com.adventrue.command.GoCommand
import com.adventrue.command.QuitCommand
import com.adventrue.common.CommandReader
import com.adventrue.common.MessageWriter
import com.adventrue.exception.InvalidCommandException

class TextAdventureGame(
    private val player: Player,
    private val commands: List<Command>,
    private val messageWriter: MessageWriter = MessageWriter(),
) {
    private var isRunning: Boolean = true

    fun run() {
        welcome()
        play()
        messageWriter.printGoodbyeMessage()
    }

    fun stop() {
        isRunning = false
    }

    private fun welcome() {
        messageWriter.printWelcomeMessage()
        messageWriter.printRoomMessage(player.getCurrentRoom())
        messageWriter.printHelpMessage()
    }

    private fun play() {
        val commandReader = CommandReader()
        val commandParser = CommandParser(commands)

        while (isRunning) {
            try {
                val input = commandReader.readInput()
                val command = commandParser.parse(input)
                val commandContext = createCommandContext(command)
                command.execute(commandContext)
            } catch (e: Exception) {
                messageWriter.print(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    // 이게 아닌데..
    private fun createCommandContext(command: Command): CommandContext {
        return when (command) {
            is GoCommand -> CommandContext(player = player)
            is QuitCommand -> CommandContext(game = this)
            else -> {
                throw InvalidCommandException("커맨드 컨텍스트를 구성할 수 없는 커맨드입니다.")
            }
        }
    }
}
