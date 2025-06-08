package com.schedule

import java.time.DayOfWeek
import java.time.LocalTime

class MonthlyMeeting(
    private val id: Long,
    private var meetingName: String,
    private var dayOfWeek: DayOfWeek,
    private var startTime: LocalTime,
    private var endTime: LocalTime,
    private var ordinal: Ordinal,
) : RecurringMeeting {

    override fun getId(): Long {
        return id
    }

    override fun getStartTime(): LocalTime {
        return startTime
    }

    override fun getEndTime(): LocalTime {
        return endTime
    }

    override fun getDayOfWeek(): DayOfWeek {
        return dayOfWeek
    }

    override fun validateOverlap(other: RecurringMeeting) {
        super.validateOverlap(other)

        if (other is MonthlyMeeting && other.ordinal == this.ordinal) {
            throw IllegalArgumentException("Monthly meeting times overlap with another monthly meeting on the same day and ordinal.")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MonthlyMeeting) return false

        if (id != other.id) return false // ????

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
