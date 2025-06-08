package com.adventrue.command

import com.adventrue.game.TextAdventureGame

class QuitCommand : Command {

    override val name: String = "quit"

    override fun execute(commandContext: CommandContext){
        val game = getGame(commandContext)

        game.stop()
    }

    private fun getGame(commandContext: CommandContext): TextAdventureGame {
        return commandContext.game ?: throw IllegalStateException("Game not found in command context")
    }
}