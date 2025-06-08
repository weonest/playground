package com.schedule

import java.time.DayOfWeek
import java.time.LocalTime

class MonthlyMeeting(
    private val id: Long,
    private val meetingName: String,
    private val dayOfWeek: DayOfWeek,
    private val startTime: LocalTime,
    private val endTime: LocalTime,
    private val ordinal: Ordinal,
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
}
