package com.schedule

data class Ordinal(val value: Int) {
    init {
        require(value in 1..5) { "Ordinal value must be between 1 and 5." }
    }
}
