package com.adventrue.command

import com.adventrue.TextAdventureGame

class QuitCommand : Command {
    override val name: String
        get() = "quit"

    override fun execute(argument: String?): Int {
        return -1
    }
}