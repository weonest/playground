package com.schedule

import java.time.DayOfWeek
import java.util.concurrent.ConcurrentHashMap

class MeetingRepository(
    private val meetings: ConcurrentHashMap<Long, RecurringMeeting> = ConcurrentHashMap(),
    private val dayOfWeekIndex: ConcurrentHashMap<DayOfWeek, List<RecurringMeeting>> = ConcurrentHashMap()
) {
    fun insert(recurringMeeting: RecurringMeeting) {
        meetings[recurringMeeting.getId()] = recurringMeeting

        dayOfWeekIndex[recurringMeeting.getDayOfWeek()] =
            dayOfWeekIndex.getOrDefault(recurringMeeting.getDayOfWeek(), emptyList()) + recurringMeeting
    }

    fun findByDayOfWeek(dayOfWeek: DayOfWeek): List<RecurringMeeting> {
        return dayOfWeekIndex.getOrDefault(dayOfWeek, emptyList())
    }
}