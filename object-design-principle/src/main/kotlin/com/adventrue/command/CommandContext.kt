package com.adventrue.command

import com.adventrue.game.Player
import com.adventrue.game.TextAdventureGame

data class CommandContext(
    val game: TextAdventureGame? = null,
    val player: Player? = null,
)
