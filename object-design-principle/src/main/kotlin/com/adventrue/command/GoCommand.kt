package com.adventrue.command

import com.adventrue.exception.InvalidCommandException

class GoCommand : Command {
    enum class Direction(val value: String) {
        NORTH("north"),
        SOUTH("south"),
        EAST("east"),
        WEST("west");

        companion object {
            fun from(value: String): Direction? {
                return entries.find { it.value == value }
            }
        }
    }

    override val name: String
        get() = "go"

    override fun execute(argument: String?): Int {
        if (argument == null) {
            throw InvalidCommandException("go 명령어는 방향을 지정해야 합니다.")
        }
        val direction = Direction.from(argument)
            ?: throw InvalidCommandException("go 명령어는 north, south, east, west 중 하나의 방향을 지정해야 합니다.")

        return when (direction) {
            Direction.NORTH -> 0
            Direction.SOUTH -> 1
            Direction.EAST -> 2
            Direction.WEST -> 3
        }

        return 1
    }
}