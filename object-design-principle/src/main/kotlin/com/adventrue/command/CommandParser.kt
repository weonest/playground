package com.adventrue.command

import com.adventrue.exception.InvalidCommandException
import com.adventrue.game.Direction

class CommandParser(private val commands: List<Command>) {

    fun parse(input: String): Command {
        val userCommand = split(input)

        val command = commands.find { it.name == userCommand[0] }
            ?: throw InvalidCommandException("존재하지 않는 커맨드입니다 : ${split(input)[0]}")

        if (command is GoCommand) {
           preProcessGoCommand(command, userCommand)
        }

        return command
    }

    private fun preProcessGoCommand(command: Command, userCommand: List<String>) {
        if (command is GoCommand) {
            if (userCommand.size < 2) {
                throw InvalidCommandException("방향을 지정하지 않았습니다. 사용법: go [방향]")
            }
            val direction = Direction.fromString(userCommand[1])
            command.setDirection(direction)
        }
    }

    private fun split(input: String): List<String> {
        return input.lowercase().trim().split(" ")
    }
}
