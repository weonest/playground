package com.adventrue.command

import com.adventrue.TextAdventureGame

interface Command {
    val name: String

    fun execute(argument: String?): Int

}