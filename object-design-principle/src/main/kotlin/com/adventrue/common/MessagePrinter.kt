package com.adventrue.common

import com.adventrue.room.Room

class MessagePrinter {

    fun printWelcomeMessage(startAreaName: String, startAreaDescription: String) {
        val message = """
            환영합니다!
            당신은 [${startAreaName}]에 있습니다.
            $startAreaDescription
            다음 명령어를 사용할 수 있습니다.
            go { north | south | east | west } - 이동,
            quit - 게임 종료
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
}