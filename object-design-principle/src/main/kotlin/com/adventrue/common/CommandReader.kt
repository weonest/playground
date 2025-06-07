package com.adventrue.common

import java.util.Scanner

class CommandReader(
    val scanner: Scanner = Scanner(System.`in`),
) {
    fun readInput(): String {
        print("> ")
        return scanner.nextLine()
    }
}