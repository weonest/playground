package com.adventrue.command

import com.adventrue.exception.InvalidCommandException

class GoCommand : Command {
    override val name: String
        get() = "go"

    override fun execute(args: String?) {
        if (args == null) {
            throw InvalidCommandException("go 명령어는 방향을 지정해야 합니다.")
        }
    }
}