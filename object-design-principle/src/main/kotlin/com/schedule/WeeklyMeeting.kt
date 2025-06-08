package com.schedule

import java.time.DayOfWeek
import java.time.LocalTime

class WeeklyMeeting(
    private val id: Long,
    private var meetingName: String,
    private var dayOfWeek: DayOfWeek,
    private var startTime: LocalTime,
    private var endTime: LocalTime,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WeeklyMeeting) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
