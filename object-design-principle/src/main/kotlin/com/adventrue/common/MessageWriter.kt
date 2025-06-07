package com.adventrue.common

import com.adventrue.room.Room

class MessageWriter {

    fun print(message: String) {
        println(message)
    }

    fun printWelcomeMessage() {
        val message = """
            환영합니다!
        """.trimIndent()
        println(message)
    }

    fun printRoomMessage(room: Room) {
        val message = """
            당신은 [${room.name}]에 있습니다.
            ${room.description}
        """.trimIndent()
        println(message)
    }

    fun printHelpMessage() {
        val message = """
            다음 명령어를 사용할 수 있습니다.
            go { north | south | east | west } - 이동,
            quit - 게임 종료
        """.trimIndent()
        println(message)
    }

    fun printBlockedMessage() {
        println("그 방향으로는 이동할 수 없습니다.")
    }

    fun printGoodbyeMessage() {
        println("게임을 종료합니다. 안녕히 가세요!")
    }
}
