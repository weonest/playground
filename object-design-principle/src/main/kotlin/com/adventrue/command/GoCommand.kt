package com.adventrue.command

import com.adventrue.common.MessageWriter
import com.adventrue.game.Direction
import com.adventrue.game.Player

class GoCommand(
    private val messageWriter: MessageWriter = MessageWriter(),
    private var direction: Direction = Direction.INIT
) : Command {

    override val name: String = "go"

    fun setDirection(direction: Direction) {
        this.direction = direction
    }

    override fun execute(commandContext: CommandContext) {
        val player = getPlayer(commandContext)

        if (player.canMove(direction)) {
            player.move(direction)
            messageWriter.printRoomMessage(player.getCurrentRoom())
            return
        }

        messageWriter.printBlockedMessage()
    }

    private fun getPlayer(commandContext: CommandContext): Player {
        return commandContext.player ?: throw IllegalStateException("Player not found in command context")
    }
}