package com.adventrue.command

import com.adventrue.exception.InvalidCommandException
import com.adventrue.game.Direction
import com.adventrue.game.Player
import com.adventrue.game.Position
import com.adventrue.game.Size
import com.adventrue.game.World
import com.adventrue.room.Bridge
import com.adventrue.room.Castle
import com.adventrue.room.Cave
import com.adventrue.room.EmptyRoom
import com.adventrue.room.Hill
import com.adventrue.room.Pond
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GoCommandTest {

    private var commandContext = CommandContext(
        player = Player(
            world = World(
                size = Size(2, 3),
                rooms = listOf(
                    Pond(), EmptyRoom(), Hill(), Castle(), Bridge(), Cave(),
                )
            ),
            position = Position(0, 0)
        )
    )

    @Test
    fun `execute go command`() {
        Direction.entries.forEach { direction ->
            if (direction == Direction.INIT) return@forEach
            val goCommand = GoCommand(direction = direction)
            assertDoesNotThrow { goCommand.execute(commandContext) }
        }
    }
}
