package com.schedule

interface RecurringMeeting {

    fun getId(): Long

    fun getStartTime(): java.time.LocalTime

    fun getEndTime(): java.time.LocalTime

    fun getDayOfWeek(): java.time.DayOfWeek

    fun validateOverlap(other: RecurringMeeting) {
        if (other.getDayOfWeek() != this.getDayOfWeek()) {
            throw IllegalArgumentException("Cannot validate time against a meeting on a different day of the week.")
        }

        val otherStartTime = other.getStartTime()
        val otherEndTime = other.getEndTime()

        if (getStartTime().isAfter(otherEndTime) || getEndTime().isBefore(otherStartTime)) {
            throw IllegalArgumentException("Meeting times overlap with another meeting on the same day.")
        }
    }

}
