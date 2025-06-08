package com.adventrue.command

import com.adventrue.exception.InvalidCommandException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CommandParserTest {

    private val commandParser = CommandParser(listOf(GoCommand(), QuitCommand()))

    @Test
    fun `parse valid command`() {
        val input = "go north"
        val command = commandParser.parse(input)
        assertNotNull(command)
        assertEquals("go", command.name)
        assertTrue(command is GoCommand)
    }

    @Test
    fun `parse invalid command`() {
        val input = "invalid command"
        assertThrows(InvalidCommandException::class.java) { commandParser.parse(input) }
    }
}
