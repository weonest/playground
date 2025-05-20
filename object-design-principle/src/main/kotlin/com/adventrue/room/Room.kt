package com.adventrue.room

abstract class Room {
    val name: String
    val description: String

    constructor(name: String, description: String) {
        this.name = name
        this.description = description
    }
}
