package com.schedule

import java.time.DayOfWeek
import java.util.concurrent.ConcurrentHashMap

class MeetingRepository(
    private val meetings: ConcurrentHashMap<Long, RecurringMeeting> = ConcurrentHashMap(),
    private val dayOfWeekIndex: ConcurrentHashMap<DayOfWeek, Set<RecurringMeeting>> = ConcurrentHashMap()
) {
    fun insert(recurringMeeting: RecurringMeeting) {
        meetings[recurringMeeting.getId()] = recurringMeeting

        dayOfWeekIndex[recurringMeeting.getDayOfWeek()] =
            dayOfWeekIndex.getOrDefault(recurringMeeting.getDayOfWeek(), emptySet()) + recurringMeeting
    }

    fun findByDayOfWeek(dayOfWeek: DayOfWeek): Set<RecurringMeeting> {
        return dayOfWeekIndex.getOrDefault(dayOfWeek, emptySet())
    }
}