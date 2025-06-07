package com.adventrue.command

interface Command {
    val name: String

    fun execute(commandContext: CommandContext)
}
