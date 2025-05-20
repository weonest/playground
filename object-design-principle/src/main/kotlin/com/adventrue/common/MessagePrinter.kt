package com.adventrue.common

class MessagePrinter {

    fun printWelcomeMessage(startAreaName: String) {
        val message = """
            환영합니다!
            당신은 [${startAreaName}]에 있습니다.
            저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다.
            다음 명령어를 사용할 수 있습니다.
            go { north | south | east | west } - 이동,
            quit - 게임 종료
        """.trimIndent()
    }
}