package com.adventrue.game

import com.adventrue.room.Room

class Player(
    var world: World,
    var position: Position,
){
    fun canMove(direction: Direction): Boolean {
        return !world.isBlocked(position.shift(direction))
    }

    fun move(direction: Direction) {
        if (!canMove(direction)) {
            throw IllegalStateException("해당 방향으로는 이동할 수 없습니다 : $direction")
        }
        this.position = position.shift(direction)
    }

    fun getCurrentRoom(): Room {
        return world.getRoomByPosition(position)
    }
}
