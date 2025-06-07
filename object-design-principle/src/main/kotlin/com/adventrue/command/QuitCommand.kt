package com.adventrue.command

class QuitCommand : Command {

    override val name: String = "quit"

    override fun execute(commandContext: CommandContext){
        val game = commandContext.game ?: throw IllegalStateException("Game not found in command context")

        game.stop()
    }
}