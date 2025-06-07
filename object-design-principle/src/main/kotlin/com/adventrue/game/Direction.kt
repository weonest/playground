package com.adventrue.game

enum class Direction(val value: String) {
    INIT("init"),
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    companion object {
        fun fromString(value: String): Direction {
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("잘못된 방향입니다 : $value")
        }
    }
}
