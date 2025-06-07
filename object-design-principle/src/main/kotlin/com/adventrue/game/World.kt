package com.adventrue.game

import com.adventrue.room.Room

class World(
    val size: Size,
    val rooms: List<Room>,
) {
    fun getRoomByPosition(position: Position): Room {
        return rooms[getRoomIndexByPosition(position)]
    }

    fun isBlocked(position: Position): Boolean {
        return position.x < 0 || position.x >= size.width ||
                position.y < 0 || position.y >= size.height
    }

    private fun getRoomIndexByPosition(position: Position): Int {
        return position.x + (position.y * size.width % size.height)

    }
}