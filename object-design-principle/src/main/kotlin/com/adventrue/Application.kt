package com.adventrue

import com.adventrue.command.Command
import com.adventrue.command.GoCommand
import com.adventrue.command.QuitCommand
import com.adventrue.common.MessageWriter
import com.adventrue.game.Direction
import com.adventrue.game.Player
import com.adventrue.game.Position
import com.adventrue.game.Size
import com.adventrue.game.TextAdventureGame
import com.adventrue.game.World
import com.adventrue.room.Bridge
import com.adventrue.room.Castle
import com.adventrue.room.Cave
import com.adventrue.room.EmptyRoom
import com.adventrue.room.Hill
import com.adventrue.room.Pond
import com.adventrue.room.Room

class Application

fun main() {
    TextAdventureGame(
        player = initPlayer(),
        commands = initCommands(),
        messageWriter = MessageWriter(),
    ).run()
}

fun initCommands(): List<Command> {
    return listOf(
        GoCommand(direction = Direction.INIT), QuitCommand()
    )
}

fun initPlayer(): Player {
    return Player(initWorld(), initPosition())
}

fun initPosition(): Position {
    return Position(0, 0)
}

fun initWorld(): World {
    return World(initSize(),  initRooms())
}

fun initSize(): Size {
    return Size(2, 3)
}

fun initRooms(): List<Room> {
    return listOf(
        Pond(), EmptyRoom(), Hill(), Castle(), Bridge(), Cave(),
    )
}
