package com.schedule

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SchedulerTest {
    private val meetingRepository = MeetingRepository()

    @Test
    fun `test schedule with no overlap`() {
        val scheduler = Scheduler(meetingRepository)

        val weeklyMeeting = WeeklyMeeting(
            id = 1L,
            meetingName = "Weekly Standup",
            dayOfWeek = java.time.DayOfWeek.MONDAY,
            startTime = java.time.LocalTime.of(9, 0),
            endTime = java.time.LocalTime.of(10, 0)
        )
        scheduler.schedule(weeklyMeeting)

        assertEquals(1, meetingRepository.findByDayOfWeek(java.time.DayOfWeek.MONDAY).size)
    }

    @Test
    fun `test schedule with overlap throws exception`() {
        val scheduler = Scheduler(meetingRepository)

        val weeklyMeeting = WeeklyMeeting(
            id = 1L,
            meetingName = "Weekly Standup",
            dayOfWeek = java.time.DayOfWeek.MONDAY,
            startTime = java.time.LocalTime.of(9, 0),
            endTime = java.time.LocalTime.of(10, 0)
        )
        scheduler.schedule(weeklyMeeting)

        val monthlyMeeting = MonthlyMeeting(
            id = 2L,
            meetingName = "Monthly Planning",
            dayOfWeek = java.time.DayOfWeek.MONDAY,
            startTime = java.time.LocalTime.of(8, 30),
            endTime = java.time.LocalTime.of(10, 30),
            ordinal = Ordinal(1)
        )

        assertThrows(IllegalArgumentException::class.java) {
            scheduler.schedule(monthlyMeeting)
        }
    }
}